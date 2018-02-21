package org.miejski.keepit.domain.aggregate

import org.miejski.keepit.domain.common.commands.Command

interface DomainRepository<T : Aggregate> {
    fun get(aggregateNameID: AggregateNameID, aggregateID: String): T?
    fun getAll(aggregateNameID: AggregateNameID): List<T>
    fun update(aggregateNameID: AggregateNameID, aggregate: T, command: Command): T
}


