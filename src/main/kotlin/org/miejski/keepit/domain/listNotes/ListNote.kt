package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.aggregate.Aggregate
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.listNotes.create.ListNoteCreatedEvent
import org.miejski.keepit.domain.listNotes.items.Item


class ListNote : Aggregate {

    private lateinit var items: List<Item>
    private lateinit var id: String

    override fun ID(): String {
        return id
    }

    fun toDto(): ListNoteDTO {
        return ListNoteDTO(ID(), items.map { it.toDto() })
    }

    override fun applyEvent(event: Event) {
        when (event) {
            is ListNoteCreatedEvent -> {
                this.items = event.content.map { Item(it.id, it.content) }
                this.id = event.targetAggID()
            }
        }
    }
}

fun NewListNote(): ListNote {
    return ListNote()
}