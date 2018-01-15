package org.miejski.keepit.api

data class NotesDto(val notes: List<NoteDto>)

data class NoteDto(val content: String)