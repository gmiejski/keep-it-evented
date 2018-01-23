package org.miejski.keepit.domain.notes.repository

import org.miejski.keepit.domain.notes.NewNote
import org.miejski.keepit.domain.notes.Note
import org.miejski.keepit.domain.notes.CreateNotesAggregateID
import org.miejski.keepit.domain.notes.commands.CreateNoteCommand
import org.miejski.keepit.domain.notes.commands.NotesCommandHandler
import org.miejski.keepit.domain.notes.events.NotesEventsHandler
import org.miejski.keepit.infrastructure.eventstore.EventStore

class NotesRepository(
    val commandHandler: NotesCommandHandler,
    val notesEventsHandler: NotesEventsHandler,
    val eventStore: EventStore) : NotesAgregateRepository {

    override fun getAll(user: String): List<Note> {
        val allEvents = eventStore.getAll(CreateNotesAggregateID(user))

        return allEvents.groupBy { it.ID() }
            .map { notesEventsHandler.applyEvents(NewNote(), it.value) }
    }

    override fun createNote(user: String, command: CreateNoteCommand): Note {
        val newNote = NewNote()
        val events = commandHandler.applyCommand(user, newNote, command)
        eventStore.saveAll(CreateNotesAggregateID(user), events)
        return notesEventsHandler.applyEvents(newNote, events)
    }
}