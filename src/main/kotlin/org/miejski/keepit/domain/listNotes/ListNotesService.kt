package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.aggregate.DomainRepository
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand

class ListNotesService(val listNotesRepository: DomainRepository<ListNote>) {
    fun createListNote(user: String, command: CreateListNoteCommand): ListNote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun create(): ListNote {
        TODO()
    }
}