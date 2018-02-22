package org.miejski.keepit.domain.aggregate

import org.miejski.keepit.domain.common.events.Event

interface Aggregate {
    fun ID(): String
    fun applyEvent(event: Event)
}

fun aggregateNameID(user: String, aggName: String): AggregateNameID {
    return object : AggregateNameID {
        override fun toString(): String {
            return "$user:$aggName"
        }
    }
}

interface AggregateNameID {
    override fun toString(): String
}
