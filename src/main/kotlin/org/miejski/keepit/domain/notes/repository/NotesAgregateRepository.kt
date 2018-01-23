package org.miejski.keepit.domain.notes.repository

import org.miejski.keepit.domain.notes.Note
import org.miejski.keepit.domain.notes.commands.CreateNoteCommand

interface NotesAgregateRepository {
    fun getAll(user: String): List<Note>
    fun createNote(user: String, command: CreateNoteCommand): Note
}

