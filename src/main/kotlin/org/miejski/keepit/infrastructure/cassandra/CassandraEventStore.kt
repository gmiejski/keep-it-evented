package org.miejski.keepit.infrastructure.cassandra

import com.datastax.driver.core.Session
import com.datastax.driver.mapping.MappingManager
import org.miejski.keepit.domain.notes.NotesAggregateID
import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.infrastructure.eventstore.EventStore


class CassandraEventStore(val noteEventAccessor: NoteEventAccessor) : EventStore {

    override fun saveEvent(aggregateID: NotesAggregateID, event: Event) {

//        noteEventAccessor.save(aggregateID.toString(), event.ID(), )

//        eventsMap[aggregateID] = eventsMap.getOrDefault(aggregateID, listOf()).plus(event)
    }

    override fun saveAll(aggregateID: NotesAggregateID, events: List<Event>) {
//        eventsMap[aggregateID] = eventsMap.getOrDefault(aggregateID, listOf()).plus(events)
    }

    override fun getAll(aggregateID: NotesAggregateID): List<Event> {
        return listOf()
//        return eventsMap.getOrDefault(aggregateID, listOf())
    }
}