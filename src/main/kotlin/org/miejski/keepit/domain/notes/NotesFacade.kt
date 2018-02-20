package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NotesDto
import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.listNotes.ListNote
import org.miejski.keepit.domain.listNotes.ListNotesService
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand

class NotesFacade(val notesService: NotesService, val listNotesService: ListNotesService) {

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

    fun createListNote(user: String, command: CreateListNoteCommand): ListNote {
        return listNotesService.createListNote(user, command)
    }
}



