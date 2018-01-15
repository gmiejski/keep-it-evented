package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.api.NotesDto
import org.miejski.keepit.domain.notes.commands.CreateNoteCommand

class NotesFacade {

    fun createNote(command: CreateNoteCommand) {

    }

    fun getNotes(): NotesDto {
        return NotesDto(listOf(NoteDto("someNote")))
    }
}