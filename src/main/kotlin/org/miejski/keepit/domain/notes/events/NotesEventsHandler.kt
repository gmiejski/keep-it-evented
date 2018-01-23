package org.miejski.keepit.domain.notes.events

import org.miejski.keepit.domain.notes.Note

class NotesEventsHandler {
    fun applyEvents(newNote: Note, events: List<Event>): Note {
        events.forEach { newNote.applyEvent(it) }
        return newNote
    }
}