package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.AggregateRepository
import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand

class NotesService(val notesAgregateRepository: AggregateRepository<Note>) {

    fun createNote(user: String, command: CreateNoteCommand): Note {
        return notesAgregateRepository.update(user, NewNote(), command)
    }

    fun editNote(user: String, command: EditNoteCommand): Note {
        val note = notesAgregateRepository.get(user, command.noteID) ?: throw NoteNotFound(user, command.noteID)
        return notesAgregateRepository.update(user, note, command)
    }

    fun getAll(user: String, filters: Set<NoteType>): List<Note> {
        return notesAgregateRepository.getAll(user).filter { filters.matchedBy(it) }
    }

    fun archiveNote(user: String, command: ArchiveNoteCommand): Note {
        val note = notesAgregateRepository.get(user, command.noteID) ?: throw NoteNotFound(user, command.noteID)
        return notesAgregateRepository.update(user, note, command)
    }
}

private fun Set<NoteType>.matchedBy(note: Note): Boolean {
    return this.isEmpty() || this.any { it == note.type }
}
