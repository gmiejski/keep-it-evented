package org.miejski.keepit.infrastructure.eventstore

import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.UserNotesAggregateID
import java.util.concurrent.ConcurrentHashMap

class InMemoryEventStore : EventStore {
    private var eventsMap: MutableMap<UserNotesAggregateID, List<Event>> = ConcurrentHashMap()

    override fun get(aggregateIDUser: UserNotesAggregateID, noteID: String): List<Event> {
        return eventsMap.getOrDefault(aggregateIDUser, listOf()).filter { it.targetAggID() == noteID }
    }

    override fun saveEvent(aggregateIDUser: UserNotesAggregateID, event: Event) {
        eventsMap[aggregateIDUser] = eventsMap.getOrDefault(aggregateIDUser, listOf()).plus(event)
    }

    override fun saveAll(aggregateIDUser: UserNotesAggregateID, events: List<Event>) {
        eventsMap[aggregateIDUser] = eventsMap.getOrDefault(aggregateIDUser, listOf()).plus(events)
    }

    override fun getAll(aggregateIDUser: UserNotesAggregateID): List<Event> {
        return eventsMap.getOrDefault(aggregateIDUser, listOf())
    }
}