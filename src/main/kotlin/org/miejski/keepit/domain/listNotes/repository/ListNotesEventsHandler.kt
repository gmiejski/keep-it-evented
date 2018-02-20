package org.miejski.keepit.domain.listNotes.repository

import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.listNotes.ListNote

class ListNotesEventsHandler { // TODO unify to abstract types
    fun applyEvents(newNote: ListNote, events: List<Event>): ListNote {
        events.forEach { newNote.applyEvent(it) }
        return newNote
    }
}