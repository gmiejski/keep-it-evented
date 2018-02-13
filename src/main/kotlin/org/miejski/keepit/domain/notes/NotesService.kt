package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.notes.commands.CreateNoteCommand
import org.miejski.keepit.domain.notes.commands.EditNoteCommand
import org.miejski.keepit.domain.notes.repository.NotesAgregateRepository

class NotesService(val notesAgregateRepository: NotesAgregateRepository) {

    fun createNote(user: String, command: CreateNoteCommand): Note {
        return notesAgregateRepository.update(user, NewNote(), command)
    }

    fun editNote(user: String, command: EditNoteCommand): Note {
        val note = notesAgregateRepository.get(user, command.noteID) ?: throw RuntimeException("Note not found") // TODO name exception

        return notesAgregateRepository.update(user, note, command)
    }

    fun getAll(user: String): List<Note> {
        return notesAgregateRepository.getAll(user)
    }
}
