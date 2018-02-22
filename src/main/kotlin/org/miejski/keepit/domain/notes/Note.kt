package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.aggregate.Aggregate
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.notes.archive.NoteArchivedEvent
import org.miejski.keepit.domain.notes.create.NoteCreatedEvent
import org.miejski.keepit.domain.notes.edit.NoteEditedEvent


class Note(private var content: String) : Aggregate {

    private lateinit var id: String
    var type: NoteType = NoteType.STANDARD
        private set

    override fun ID(): String {
        return id
    }

    fun toDto(): NoteDto {
        return NoteDto(ID(), content)
    }

    override fun applyEvent(event: Event) {
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
    }
}

fun NewNote(): Note {
    return Note("")
}