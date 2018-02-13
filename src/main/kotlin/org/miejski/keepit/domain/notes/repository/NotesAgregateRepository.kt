package org.miejski.keepit.domain.notes.repository

import org.miejski.keepit.domain.notes.Note
import org.miejski.keepit.domain.notes.commands.Command
import org.miejski.keepit.domain.notes.commands.CreateNoteCommand

interface NotesAgregateRepository {
    fun get(user: String, noteID: String): Note?
    fun getAll(user: String): List<Note>
    fun update(user: String, note: Note, command: Command): Note
}

