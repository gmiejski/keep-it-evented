package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NotesDto
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand

class NotesFacade(val notesService: NotesService) {

    fun createNote(user: String, command: CreateNoteCommand): Note {
        return notesService.createNote(user, command)
    }

    fun editNote(user: String, command: EditNoteCommand): Note {
        return notesService.editNote(user, command)
    }

    fun getNotes(user: String, filters: Set<NoteType>): NotesDto {
        val notes = notesService.getAll(user, filters)
        return NotesDto(notes.map { it.toDto() })
    }

    fun archiveNote(user: String, command: ArchiveNoteCommand): Note {
        return notesService.archiveNote(user, command)
    }
}



