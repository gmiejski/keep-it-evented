package org.miejski.keepit.infrastructure.cassandra.eventstore

import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.NoteAggregateID
import java.util.concurrent.ConcurrentHashMap

class InMemoryEventStore : EventStore {

    private var eventsMap: MutableMap<NoteAggregateID, List<Event>> = ConcurrentHashMap()

    override fun saveEvent(aggregateID: NoteAggregateID, event: Event) {
        eventsMap[aggregateID] = eventsMap.getOrDefault(aggregateID, listOf()).plus(event)
    }

    override fun saveAll(aggregateID: NoteAggregateID, events: List<Event>) {
        eventsMap[aggregateID] = eventsMap.getOrDefault(aggregateID, listOf()).plus(events)
    }

    override fun getAll(aggregateID: NoteAggregateID): List<Event> {
        return eventsMap.getOrDefault(aggregateID, listOf())
    }
}