package org.miejski.keepit.domain.aggregate

import org.miejski.keepit.domain.common.events.Event

interface EventStore {
    fun getAll(aggregateIDUser: AggregateNameID): List<Event>
    fun get(aggregateNameID: AggregateNameID, aggregateID: String): List<Event>
    fun saveEvent(aggregateIDUser: AggregateNameID, event: Event)
    fun saveAll(aggregateIDUser: AggregateNameID, events: List<Event>)
}