package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.notes.Note

class ListNotesFacade(val listNotesService: ListNotesService) {
    fun createListNote(user: String, command: CreateListNoteCommand): ListNote {
        return listNotesService.createListNote(user, command)
    }
}