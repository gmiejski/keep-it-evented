package org.miejski.keepit.domain.listNotes

data class ListNotesDTO(val notes: List<ListNoteDTO>) {
    fun getNote(id: String): ListNoteDTO? {
        return notes.find { it.id == id }
    }
}

data class ListNoteDTO(val id: String, val items: List<ListItemDTO>) {
    fun getItem(id: String): ListItemDTO? {
        return items.find { it.id == id }
    }
}

data class ListItemDTO(val id: String, val content: String, val isCompleted: Boolean)
