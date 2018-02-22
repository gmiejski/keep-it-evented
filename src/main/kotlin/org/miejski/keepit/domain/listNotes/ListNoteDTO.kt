package org.miejski.keepit.domain.listNotes

data class ListNotesDTO(val notes: List<ListNoteDTO>)

data class ListNoteDTO(val id: String, val items: List<ListItemDTO>)

data class ListItemDTO(val id: String, val content: String)
