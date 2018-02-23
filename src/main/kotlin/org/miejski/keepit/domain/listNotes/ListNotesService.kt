package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.aggregate.DomainRepository
import org.miejski.keepit.domain.listNotes.complete.CompleteItemCommand
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.items.AddListItemCommand
import org.miejski.keepit.domain.notes.NoteNotFound

class ListNotesService(val listNotesRepository: DomainRepository<ListNote>) {
    fun createListNote(user: String, command: CreateListNoteCommand): ListNoteDTO {
        return listNotesRepository.update(CreateListNotesAggregateID(user), NewListNote(), command).toDto()
    }

    fun getAll(user: String): ListNotesDTO {
        return ListNotesDTO(listNotesRepository.getAll(CreateListNotesAggregateID(user)).map { it.toDto() })
    }

    fun addListItem(user: String, command: AddListItemCommand): ListNoteDTO {
        val note = listNotesRepository.get(CreateListNotesAggregateID(user), command.noteID) ?: throw NoteNotFound(user, command.noteID)
        return listNotesRepository.update(CreateListNotesAggregateID(user), note, command).toDto()
    }

    fun completeItem(user: String, command: CompleteItemCommand) {
        val note = listNotesRepository.get(CreateListNotesAggregateID(user), command.noteID) ?: throw NoteNotFound(user, command.noteID)
        val item = note.getItem(command.itemID) ?: throw ListItemNotFound(user, command.noteID, command.itemID)
        listNotesRepository.update(CreateListNotesAggregateID(user), note, command)
    }
}

class ListItemNotFound(user: String, noteID: String, itemID: String) : RuntimeException("List item: $itemID not found in user's $user note: $noteID")
