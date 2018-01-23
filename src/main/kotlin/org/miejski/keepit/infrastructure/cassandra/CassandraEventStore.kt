package org.miejski.keepit.infrastructure.cassandra

import org.miejski.keepit.domain.notes.UserNotesAggregateID
import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.infrastructure.eventstore.EventStore


class CassandraEventStore(val noteEventAccessor: NoteEventAccessor, val eventSerializer: EventSerializer) : EventStore {

    override fun saveEvent(aggregateIDUser: UserNotesAggregateID, event: Event) {
        val saved = noteEventAccessor.save(aggregateIDUser.toString(), event.ID(), eventSerializer.serialize(event))
        println(saved)
    }

    override fun saveAll(aggregateIDUser: UserNotesAggregateID, events: List<Event>) {
        events.forEach { saveEvent(aggregateIDUser, it) }
    }

    override fun getAll(aggregateIDUser: UserNotesAggregateID): List<Event> {
        val rows = noteEventAccessor.getAll(aggregateIDUser.toString())
        return rows.map { eventSerializer.deserialize(it.eventBlob.array()) }
    }
}