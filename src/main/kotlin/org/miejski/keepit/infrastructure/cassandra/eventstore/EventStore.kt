package org.miejski.keepit.infrastructure.cassandra.eventstore

import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.NotesAggregateID

interface EventStore {

    fun getAll(aggregateID: NotesAggregateID): List<Event>
    fun saveEvent(aggregateID: NotesAggregateID, event: Event)
    fun saveAll(aggregateID: NotesAggregateID, events: List<Event>)
}