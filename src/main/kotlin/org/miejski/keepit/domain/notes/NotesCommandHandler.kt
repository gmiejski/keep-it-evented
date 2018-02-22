package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.aggregate.CommandHandler
import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.common.commands.UnknownCommand
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.archive.NoteArchivedEvent
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.create.NoteCreatedEvent
import org.miejski.keepit.domain.notes.edit.EditNoteCommand
import org.miejski.keepit.domain.notes.edit.NoteEditedEvent
import java.util.*

class NotesCommandHandler : CommandHandler<Note> {
    override fun applyCommand(aggregate: Note, command: Command): List<Event> {
        return when (command) {
            is CreateNoteCommand -> listOf(NoteCreatedEvent(UUID.randomUUID().toString(), command.content))
            is EditNoteCommand -> listOf(NoteEditedEvent(command.noteID, command.content))
            is ArchiveNoteCommand -> listOf(NoteArchivedEvent(command.noteID))
            else -> throw UnknownCommand(command)
        }
    }
}