package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.domain.Aggregate
import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.notes.archive.NoteArchivedEvent
import org.miejski.keepit.domain.notes.create.NoteCreatedEvent
import org.miejski.keepit.domain.notes.edit.NoteEditedEvent



class Note(private var content: String) : Aggregate<Note> {

    private lateinit var id: String
    var type: NoteType = NoteType.STANDARD
        private set

    override fun ID(): String {
        return id
    }

    fun toDto(): NoteDto {
        return NoteDto(content)
    }

    override fun applyEvent(event: Event): Note {
        when (event) {
            is NoteCreatedEvent -> {
                this.content = event.content
                this.id = event.targetAggID()
            }
            is NoteEditedEvent -> {
                this.content = event.newContent
            }
            is NoteArchivedEvent -> {
                this.type = NoteType.ARCHIVED
            }
        }
        return this
    }
}

fun NewNote(): Note {
    return Note("")
}