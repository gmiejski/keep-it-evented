package org.miejski.keepit.domain.notes.commands

import org.miejski.keepit.domain.notes.Note
import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.events.NoteCreatedEvent
import java.util.*

class NotesCommandHandler {
    fun applyCommand(user: String, note: Note, command: Command): List<Event> {
        return when (command) {
            is CreateNoteCommand -> listOf(NoteCreatedEvent(UUID.randomUUID().toString(), command.content))
            else -> listOf()
        }
    }
}