package org.miejski.keepit.domain.notes.commands

import org.miejski.keepit.domain.notes.Note
import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.events.NoteCreatedEvent
import org.miejski.keepit.domain.notes.events.NoteEditedEvent
import java.util.*

class NotesCommandHandler {
    fun applyCommand(user: String, note: Note, command: Command): List<Event> {
        return when (command) {
            is CreateNoteCommand -> listOf(NoteCreatedEvent(UUID.randomUUID().toString(), command.content))
            is EditNoteCommand -> listOf(NoteEditedEvent(command.noteID, command.content))
            else -> listOf()
        }
    }
}