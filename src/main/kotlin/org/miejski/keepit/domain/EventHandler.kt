package org.miejski.keepit.domain

import org.miejski.keepit.domain.common.events.Event

class EventsHandler {
    fun <T : Aggregate<T>> applyEvents(newNote: T, events: List<Event>): T {
        events.forEach { newNote.applyEvent(it) }
        return newNote
    }
}