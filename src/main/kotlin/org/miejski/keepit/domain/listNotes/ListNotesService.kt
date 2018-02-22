package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.aggregate.DomainRepository
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.items.AddListItemCommand
import org.miejski.keepit.domain.notes.NoteNotFound

class ListNotesService(val listNotesRepository: DomainRepository<ListNote>) {
    fun createListNote(user: String, command: CreateListNoteCommand): ListNote {
        return listNotesRepository.update(CreateListNotesAggregateID(user), NewListNote(), command)
    }

    fun getAll(user: String): List<ListNote> {
        return listNotesRepository.getAll(CreateListNotesAggregateID(user))
    }

    fun addListItem(user: String, command: AddListItemCommand): ListNote {
        val note = listNotesRepository.get(CreateListNotesAggregateID(user), command.noteID) ?: throw NoteNotFound(user, command.noteID)
        return listNotesRepository.update(CreateListNotesAggregateID(user), note, command)
    }
}