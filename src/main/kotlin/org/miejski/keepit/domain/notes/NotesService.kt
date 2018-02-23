package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.api.NotesDto
import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.aggregate.DomainRepository
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand

class NotesService(val notesAgregateRepository: DomainRepository<Note>) {

    fun createNote(user: String, command: CreateNoteCommand): NoteDto {
        return notesAgregateRepository.update(CreateNotesAggregateID(user), NewNote(), command).toDto()
    }

    fun editNote(user: String, command: EditNoteCommand): NoteDto {
        val note = notesAgregateRepository.get(CreateNotesAggregateID(user), command.noteID) ?: throw NoteNotFound(user, command.noteID)
        return notesAgregateRepository.update(CreateNotesAggregateID(user), note, command).toDto()
    }

    fun getAll(user: String, filters: Set<NoteType>): NotesDto {
        val notes = notesAgregateRepository.getAll(CreateNotesAggregateID(user)).filter { filters.matchedBy(it) }
        return NotesDto(notes.map { it.toDto() })
    }

    fun archiveNote(user: String, command: ArchiveNoteCommand): NoteDto {
        val note = notesAgregateRepository.get(CreateNotesAggregateID(user), command.noteID) ?: throw NoteNotFound(user, command.noteID)
        return notesAgregateRepository.update(CreateNotesAggregateID(user), note, command).toDto()
    }
}

private fun Set<NoteType>.matchedBy(note: Note): Boolean {
    return this.isEmpty() || this.any { it == note.type }
}
