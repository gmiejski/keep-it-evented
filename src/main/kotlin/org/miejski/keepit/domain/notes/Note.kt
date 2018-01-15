package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NoteDto

class Note(private val content: String) {
    fun toDto(): NoteDto {
        return NoteDto(content)
    }
}