package org.miejski.keepit.domain.notes.repository

import org.miejski.keepit.domain.notes.Note
import org.miejski.keepit.domain.common.events.Event

class NotesEventsHandler {
    fun applyEvents(newNote: Note, events: List<Event>): Note {
        events.forEach { newNote.applyEvent(it) }
        return newNote
    }
}