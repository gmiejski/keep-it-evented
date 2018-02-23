package org.miejski.keepit.infrastructure.eventstore

import org.miejski.keepit.domain.aggregate.AggregateNameID
import org.miejski.keepit.domain.aggregate.EventStore
import org.miejski.keepit.domain.common.events.Event
import java.util.concurrent.ConcurrentHashMap

class InMemoryEventStore : EventStore {
    private var eventsMap: MutableMap<String, List<Event>> = ConcurrentHashMap()

    override fun get(aggregateNameID: AggregateNameID, aggregateID: String): List<Event> {
        return eventsMap.getOrDefault(aggregateNameID.toString(), listOf()).filter { it.targetAggID() == aggregateID }
    }

    override fun saveEvent(aggregateIDUser: AggregateNameID, event: Event) {
        eventsMap[aggregateIDUser.toString()] = eventsMap.getOrDefault(aggregateIDUser.toString(), listOf()).plus(event)
    }

    override fun saveAll(aggregateIDUser: AggregateNameID, events: List<Event>) {
        eventsMap[aggregateIDUser.toString()] = eventsMap.getOrDefault(aggregateIDUser.toString(), listOf()).plus(events)
    }

    override fun getAll(aggregateIDUser: AggregateNameID): List<Event> {
        return eventsMap.getOrDefault(aggregateIDUser.toString(), listOf())
    }
}