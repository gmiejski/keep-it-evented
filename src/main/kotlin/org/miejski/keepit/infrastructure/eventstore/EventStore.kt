package org.miejski.keepit.infrastructure.eventstore

import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.UserNotesAggregateID

interface EventStore {
    fun getAll(aggregateIDUser: UserNotesAggregateID): List<Event>
    fun saveEvent(aggregateIDUser: UserNotesAggregateID, event: Event)
    fun saveAll(aggregateIDUser: UserNotesAggregateID, events: List<Event>)
}