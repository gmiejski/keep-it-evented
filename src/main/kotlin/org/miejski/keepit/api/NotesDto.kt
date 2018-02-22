package org.miejski.keepit.api

data class NotesDto(val notes: List<NoteDto>)

data class NoteDto(val id: String, val content: String)