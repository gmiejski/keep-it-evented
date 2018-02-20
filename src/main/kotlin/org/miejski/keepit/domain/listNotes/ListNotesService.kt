package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.repository.ListNotesRepository

class ListNotesService(val listNotesRepository: ListNotesRepository) {
    fun createListNote(user: String, command: CreateListNoteCommand): ListNote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun create(): ListNote {
        TODO()
    }
}