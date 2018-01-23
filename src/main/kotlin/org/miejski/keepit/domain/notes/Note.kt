package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.domain.Aggregate
import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.events.NoteCreatedEvent

class Note(private var content: String): Aggregate {

    private lateinit var id: String

    override fun ID(): String {
        return id
    }

    fun toDto(): NoteDto {
        return NoteDto(content)
    }

    fun applyEvent(event: Event): Note {
        when (event) {
            is NoteCreatedEvent -> {
                this.content = event.content
                this.id = event.ID()
            }
        }
        return this
    }
}

fun NewNote(): Note {
    return Note("")
}