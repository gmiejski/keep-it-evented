package org.miejski.keepit.domain.aggregate

import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.common.events.Event

class EventSourcedRepository<T : Aggregate>(val commandHandler: CommandHandler<T>,
                                            val eventsHandler: EventsHandler,
                                            val eventStore: EventStore,
                                            val aggregateCreate: () -> T
) : DomainRepository<T> {
    override fun get(aggregateNameID: AggregateNameID, aggregateID: String): T? {
        val allEvents = eventStore.get(aggregateNameID, aggregateID)
        return when (allEvents) {
            emptyList<Event>() -> null
            else -> {
                val uninitializedAggregate = aggregateCreate()
                eventsHandler.applyEvents(uninitializedAggregate, allEvents)
            }
        }
    }

    override fun getAll(aggregateNameID: AggregateNameID): List<T> {
        val allEvents = eventStore.getAll(aggregateNameID)

        return allEvents.groupBy { it.targetAggID() }
            .map { eventsHandler.applyEvents(aggregateCreate(), it.value) }
    }

    override fun update(aggregateNameID: AggregateNameID, aggregate: T, command: Command): T {
        val events = commandHandler.applyCommand(aggregate, command)
        eventStore.saveAll(aggregateNameID, events)
        eventsHandler.applyEvents(aggregate, events)
        return aggregate
    }
}