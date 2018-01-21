package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NotesDto
import org.miejski.keepit.domain.notes.commands.CreateNoteCommand

class NotesFacade(val notesService: NotesService) {

    fun createNote(user: String, command: CreateNoteCommand): Note {
        return notesService.createNote(user, command)
    }

    fun getNotes(user: String): NotesDto {
        val notes = notesService.getAll(user)
        return NotesDto(notes.map { it.toDto() })
    }
}