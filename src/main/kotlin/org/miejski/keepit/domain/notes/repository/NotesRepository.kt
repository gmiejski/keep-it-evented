package org.miejski.keepit.domain.notes.repository

import org.miejski.keepit.domain.notes.CreateNotesAggregateID
import org.miejski.keepit.domain.notes.NewNote
import org.miejski.keepit.domain.notes.Note
import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.infrastructure.eventstore.EventStore

class NotesRepository(
    val commandHandler: NotesCommandHandler,
    val notesEventsHandler: NotesEventsHandler,
    val eventStore: EventStore) : NotesAgregateRepository {

    override fun get(user: String, noteID: String): Note? {
        val allEvents = eventStore.get(CreateNotesAggregateID(user), noteID)
        return when (allEvents) {
            emptyList<Event>() -> null
            else -> notesEventsHandler.applyEvents(NewNote(), allEvents)
        }
    }

    override fun update(user: String, note: Note, command: Command): Note {
        val events = commandHandler.applyCommand(user, note, command)
        eventStore.saveAll(CreateNotesAggregateID(user), events)
        return notesEventsHandler.applyEvents(note, events)
    }

    override fun getAll(user: String): List<Note> {
        val allEvents = eventStore.getAll(CreateNotesAggregateID(user))

        return allEvents.groupBy { it.targetAggID() }
            .map { notesEventsHandler.applyEvents(NewNote(), it.value) }
    }
}