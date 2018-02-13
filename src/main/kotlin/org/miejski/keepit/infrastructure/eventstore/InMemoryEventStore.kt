package org.miejski.keepit.infrastructure.eventstore

import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.UserNotesAggregateID
import java.util.concurrent.ConcurrentHashMap

class InMemoryEventStore : EventStore {
    override fun get(aggregateIDUser: UserNotesAggregateID, noteID: String): List<Event> {
        TODO()
        return eventsMap.getOrDefault(aggregateIDUser, listOf())
    }

    private var eventsMap: MutableMap<UserNotesAggregateID, List<Event>> = ConcurrentHashMap()

    override fun saveEvent(aggregateIDUser: UserNotesAggregateID, event: Event) {
        eventsMap[aggregateIDUser] = eventsMap.getOrDefault(aggregateIDUser, listOf()).plus(event)
    }

    override fun saveAll(aggregateIDUser: UserNotesAggregateID, events: List<Event>) {
        eventsMap[aggregateIDUser] = eventsMap.getOrDefault(aggregateIDUser, listOf()).plus(events)
    }

    override fun getAll(aggregateIDUser: UserNotesAggregateID): List<Event> {
        TODO()
        return eventsMap.getOrDefault(aggregateIDUser, listOf())
    }
}