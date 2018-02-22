package org.miejski.keepit.domain

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.api.NotesDto
import org.miejski.keepit.domain.listNotes.ListNoteDTO
import org.miejski.keepit.domain.listNotes.ListNotesDTO
import org.miejski.keepit.domain.listNotes.ListNotesService
import org.miejski.keepit.domain.listNotes.complete.CompleteItemCommand
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.items.AddListItemCommand
import org.miejski.keepit.domain.notes.NotesService
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand

class NotesFacade(private val notesService: NotesService, private val listNotesService: ListNotesService) {

    fun createNote(user: String, command: CreateNoteCommand): NoteDto {
        return notesService.createNote(user, command).toDto()
    }

    fun editNote(user: String, command: EditNoteCommand): NoteDto {
        return notesService.editNote(user, command).toDto()
    }

    fun getNotes(user: String, filters: Set<NoteType>): NotesDto {
        return NotesDto(notesService.getAll(user, filters).map { it.toDto() })
    }

    fun archiveNote(user: String, command: ArchiveNoteCommand): NoteDto {
        return notesService.archiveNote(user, command).toDto()
    }

    fun getListNotes(user: String): ListNotesDTO {
        return ListNotesDTO(listNotesService.getAll(user).map { it.toDto() })
    }

    fun createListNote(user: String, command: CreateListNoteCommand): ListNoteDTO {
        return listNotesService.createListNote(user, command).toDto()
    }

    fun addListItem(user: String, command: AddListItemCommand): ListNoteDTO {
        return listNotesService.addListItem(user, command).toDto()
    }

    fun completeItem(user: String, command: CompleteItemCommand) {
        listNotesService.completeItem(user, command)
    }

}



