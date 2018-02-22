package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.aggregate.CommandHandler
import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.common.commands.UnknownCommand
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.create.ListNoteCreatedEvent
import org.miejski.keepit.domain.listNotes.create.ListNoteItem
import org.miejski.keepit.domain.listNotes.items.AddListItemCommand
import org.miejski.keepit.domain.listNotes.items.ListItemAddedEvent
import java.util.UUID.randomUUID

class ListNotesCommandHandler : CommandHandler<ListNote> {
    override fun applyCommand(aggregate: ListNote, command: Command): List<Event> {
        return when (command) {
            is CreateListNoteCommand -> listOf(
                ListNoteCreatedEvent(randomUUID().toString(), command.tasks.toListNoteItems())
            )
            is AddListItemCommand -> listOf(ListItemAddedEvent(command.noteID, command.content.toListNoteItem()))
            else -> throw UnknownCommand(command)
        }
    }
}

private fun List<String>.toListNoteItems(): List<ListNoteItem> {
    return this.map { it.toListNoteItem() }
}

private fun String.toListNoteItem(): ListNoteItem {
    return ListNoteItem(randomUUID().toString(), this)
}