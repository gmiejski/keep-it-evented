package org.miejski.keepit.domain.listNotes.repository

import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.listNotes.ListNote
import org.miejski.keepit.domain.notes.Note
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.archive.NoteArchivedEvent
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.create.NoteCreatedEvent
import org.miejski.keepit.domain.notes.edit.EditNoteCommand
import org.miejski.keepit.domain.notes.edit.NoteEditedEvent
import java.util.*

class ListNotesCommandHandler {
    fun applyCommand(user: String, note: ListNote, command: Command): List<Event> {
        return when (command) {
            else -> throw RuntimeException("Command cannot be handled:") // TODO name
        }
    }
}