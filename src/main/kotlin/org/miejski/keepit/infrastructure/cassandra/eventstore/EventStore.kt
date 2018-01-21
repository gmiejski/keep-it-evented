package org.miejski.keepit.infrastructure.cassandra.eventstore

import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.NoteAggregateID

interface EventStore {

    fun getAll(aggregateID: NoteAggregateID): List<Event>
    fun saveEvent(aggregateID: NoteAggregateID, event: Event)
    fun saveAll(aggregateID: NoteAggregateID, events: List<Event>)
}