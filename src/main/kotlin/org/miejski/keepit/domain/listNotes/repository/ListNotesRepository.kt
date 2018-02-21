package org.miejski.keepit.domain.listNotes.repository

import org.miejski.keepit.domain.AggregateRepository
import org.miejski.keepit.domain.EventsHandler
import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.listNotes.CreateListNotesAggregateID
import org.miejski.keepit.domain.listNotes.ListNote
import org.miejski.keepit.domain.listNotes.NewListNote
import org.miejski.keepit.domain.notes.CreateNotesAggregateID
import org.miejski.keepit.infrastructure.eventstore.EventStore

class ListNotesRepository(
    val commandHandlerList: ListNotesCommandHandler,
    val listNotesEventsHandler: EventsHandler,
    val eventStore: EventStore) : AggregateRepository<ListNote> {

    override fun update(user: String, note: ListNote, command: Command): ListNote {
        val events = commandHandlerList.applyCommand(user, note, command)
        eventStore.saveAll(CreateNotesAggregateID(user), events)
        return listNotesEventsHandler.applyEvents(note, events)
    }

    override fun get(user: String, aggregateID: String): ListNote? {
        val allEvents = eventStore.get(CreateNotesAggregateID(user), aggregateID)
        return when (allEvents) {
            emptyList<Event>() -> null
            else -> listNotesEventsHandler.applyEvents(NewListNote(), allEvents)
        }
    }

    override fun getAll(user: String): List<ListNote> {
        val allEvents = eventStore.getAll(CreateListNotesAggregateID(user))

        return allEvents.groupBy { it.targetAggID() }
            .map { listNotesEventsHandler.applyEvents(NewListNote(), it.value) }
    }
}