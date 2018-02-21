package org.miejski.keepit.domain

import org.miejski.keepit.domain.common.commands.Command

interface AggregateRepository<T : Aggregate<T>> {
    fun get(user: String, aggregateID: String): T?
    fun getAll(user: String): List<T>
    fun update(user: String, note: T, command: Command): T
}
