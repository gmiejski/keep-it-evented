package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NotesDto

class NotesAggregate(private val notes: List<Note>) {

    fun toDto() : NotesDto {
        return NotesDto(notes.map { it.toDto() })
    }
}