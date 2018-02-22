package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.aggregate.DomainRepository
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand

class ListNotesService(val listNotesRepository: DomainRepository<ListNote>) {
    fun createListNote(user: String, command: CreateListNoteCommand): ListNote {
        return listNotesRepository.update(CreateListNotesAggregateID(user), NewListNote(), command)
    }

    fun getAll(user: String): List<ListNote> {
        return listNotesRepository.getAll(CreateListNotesAggregateID(user))
    }
}