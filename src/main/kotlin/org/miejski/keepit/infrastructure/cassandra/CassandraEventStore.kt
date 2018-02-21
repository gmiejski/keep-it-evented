package org.miejski.keepit.infrastructure.cassandra

import org.miejski.keepit.domain.aggregate.AggregateNameID
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.infrastructure.eventstore.EventStore
import org.miejski.keepit.serialization.EventSerializer


class CassandraEventStore(val noteEventAccessor: NoteEventAccessor, val eventSerializer: EventSerializer) : EventStore {
    override fun getAll(aggregateIDUser: AggregateNameID): List<Event> {
        val rows = noteEventAccessor.getAll(aggregateIDUser.toString())
        return rows.map { eventSerializer.deserialize(it.eventBlob.array(), it.eventMapper) }
    }

    override fun get(aggregateNameID: AggregateNameID, aggregateID: String): List<Event> {
        val rows = noteEventAccessor.get(aggregateNameID.toString(), aggregateID)
        return rows.map { eventSerializer.deserialize(it.eventBlob.array(), it.eventMapper) }
    }

    override fun saveEvent(aggregateIDUser: AggregateNameID, event: Event) {
        val (serializedEvent, serializedUsed) = eventSerializer.serialize(event)
        val saved = noteEventAccessor.save(aggregateIDUser.toString(), event.targetAggID(), event.eventTime(), serializedEvent, serializedUsed)
        println(saved)
    }

    override fun saveAll(aggregateIDUser: AggregateNameID, events: List<Event>) {
        events.forEach { saveEvent(aggregateIDUser, it) }
    }
}