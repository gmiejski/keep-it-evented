package org.miejski.keepit.domain

import org.miejski.keepit.domain.common.events.Event

interface Aggregate<T> {
    fun ID(): String
    fun applyEvent(event: Event): T
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
