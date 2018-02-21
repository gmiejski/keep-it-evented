package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.aggregate.DomainRepository
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand

class NotesService(val notesAgregateRepository: DomainRepository<Note>) {

    fun createNote(user: String, command: CreateNoteCommand): Note {
        return notesAgregateRepository.update(CreateNotesAggregateID(user), NewNote(), command)
    }

    fun editNote(user: String, command: EditNoteCommand): Note {
        val note = notesAgregateRepository.get(CreateNotesAggregateID(user), command.noteID) ?: throw NoteNotFound(user, command.noteID)
        return notesAgregateRepository.update(CreateNotesAggregateID(user), note, command)
    }

    fun getAll(user: String, filters: Set<NoteType>): List<Note> {
        return notesAgregateRepository.getAll(CreateNotesAggregateID(user)).filter { filters.matchedBy(it) }
    }

    fun archiveNote(user: String, command: ArchiveNoteCommand): Note {
        val note = notesAgregateRepository.get(CreateNotesAggregateID(user), command.noteID) ?: throw NoteNotFound(user, command.noteID)
        return notesAgregateRepository.update(CreateNotesAggregateID(user), note, command)
    }
}

private fun Set<NoteType>.matchedBy(note: Note): Boolean {
    return this.isEmpty() || this.any { it == note.type }
}
