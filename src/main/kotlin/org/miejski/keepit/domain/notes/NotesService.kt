package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.notes.commands.CreateNoteCommand
import org.miejski.keepit.domain.notes.repository.NotesAgregateRepository

class NotesService(val notesAgregateRepository: NotesAgregateRepository) {

    fun createNote(user: String, command: CreateNoteCommand) : Note{
        return notesAgregateRepository.createNote(user, command)
    }

    fun getAll(user: String): List<Note> {
        return notesAgregateRepository.getAll(user)
    }
}
