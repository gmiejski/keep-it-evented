package org.miejski.keepit.infrastructure.cassandra.eventstore

import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.NotesAggregateID
import java.util.concurrent.ConcurrentHashMap

class InMemoryEventStore : EventStore {

    private var eventsMap: MutableMap<NotesAggregateID, List<Event>> = ConcurrentHashMap()

    override fun saveEvent(aggregateID: NotesAggregateID, event: Event) {
        eventsMap[aggregateID] = eventsMap.getOrDefault(aggregateID, listOf()).plus(event)
    }

    override fun saveAll(aggregateID: NotesAggregateID, events: List<Event>) {
        eventsMap[aggregateID] = eventsMap.getOrDefault(aggregateID, listOf()).plus(events)
    }

    override fun getAll(aggregateID: NotesAggregateID): List<Event> {
        return eventsMap.getOrDefault(aggregateID, listOf())
    }
}