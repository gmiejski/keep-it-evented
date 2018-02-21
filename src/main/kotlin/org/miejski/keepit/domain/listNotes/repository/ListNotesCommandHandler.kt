package org.miejski.keepit.domain.listNotes.repository

import org.miejski.keepit.domain.aggregate.CommandHandler
import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.listNotes.ListNote

class ListNotesCommandHandler : CommandHandler<ListNote> {
    override fun applyCommand(aggregate: ListNote, command: Command): List<Event> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}