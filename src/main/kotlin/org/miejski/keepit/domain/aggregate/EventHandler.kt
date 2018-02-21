package org.miejski.keepit.domain.aggregate

import org.miejski.keepit.domain.common.events.Event

class EventsHandler {
    fun <T : Aggregate> applyEvents(newNote: T, events: List<Event>): T {
        events.forEach { newNote.applyEvent(it) }
        return newNote
    }
}