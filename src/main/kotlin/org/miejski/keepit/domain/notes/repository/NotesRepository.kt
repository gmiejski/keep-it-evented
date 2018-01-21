package org.miejski.keepit.domain.notes.repository

import org.miejski.keepit.domain.notes.NewNote
import org.miejski.keepit.domain.notes.Note
import org.miejski.keepit.domain.notes.NotesAggregateID
import org.miejski.keepit.domain.notes.commands.CreateNoteCommand
import org.miejski.keepit.domain.notes.commands.NotesCommandHandler
import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.infrastructure.cassandra.eventstore.EventStore

class NotesRepository(
    val commandHandler: NotesCommandHandler,
    val eventStore: EventStore) : NotesAgregateRepository {

    override fun getAll(user: String): List<Note> {
        val allEvents = eventStore.getAll(NotesAggregateID(user))
        return allEvents.groupBy { it.ID() }
            .map { applyEvents(NewNote(), it.value) }
    }

    override fun createNote(user: String, command: CreateNoteCommand): Note {
        val newNote = NewNote()
        val events = commandHandler.applyCommand(user, newNote, command)
        eventStore.saveAll(NotesAggregateID(user), events)
        return applyEvents(newNote, events)
    }

    private fun applyEvents(newNote: Note, events: List<Event>): Note {
        events.forEach { newNote.applyEvent(it) }
        return newNote
    }
}