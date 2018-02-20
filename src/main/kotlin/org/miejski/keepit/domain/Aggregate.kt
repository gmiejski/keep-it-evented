package org.miejski.keepit.domain

interface Aggregate {
    fun ID(): String
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
