package org.miejski.keepit.infrastructure.cassandra

import org.miejski.keepit.domain.notes.UserNotesAggregateID
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.infrastructure.eventstore.EventStore
import org.miejski.keepit.serialization.EventSerializer


class CassandraEventStore(val noteEventAccessor: NoteEventAccessor, val eventSerializer: EventSerializer) : EventStore {
    override fun getAll(aggregateIDUser: UserNotesAggregateID): List<Event> {
        val rows = noteEventAccessor.getAll(aggregateIDUser.toString())
        return rows.map { eventSerializer.deserialize(it.eventBlob.array(), it.eventMapper) }
    }

    override fun get(aggregateIDUser: UserNotesAggregateID, noteID: String): List<Event> {
        val rows = noteEventAccessor.get(aggregateIDUser.toString(), noteID)
        return rows.map { eventSerializer.deserialize(it.eventBlob.array(), it.eventMapper) }
    }

    override fun saveEvent(aggregateIDUser: UserNotesAggregateID, event: Event) {
        val (serializedEvent, serializedUsed) = eventSerializer.serialize(event)
        val saved = noteEventAccessor.save(aggregateIDUser.toString(), event.targetAggID(), event.eventTime(), serializedEvent, serializedUsed)
        println(saved)
    }

    override fun saveAll(aggregateIDUser: UserNotesAggregateID, events: List<Event>) {
        events.forEach { saveEvent(aggregateIDUser, it) }
    }
}