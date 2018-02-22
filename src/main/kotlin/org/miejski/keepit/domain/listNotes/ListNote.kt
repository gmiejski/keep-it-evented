package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.aggregate.Aggregate
import org.miejski.keepit.domain.common.events.Event
import org.miejski.keepit.domain.listNotes.complete.ListItemCompletedEvent
import org.miejski.keepit.domain.listNotes.create.ListNoteCreatedEvent
import org.miejski.keepit.domain.listNotes.create.toItem
import org.miejski.keepit.domain.listNotes.items.Item
import org.miejski.keepit.domain.listNotes.items.ListItemAddedEvent


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
            is ListItemAddedEvent -> {
                val item = event.listItem ?: throw RuntimeException("Trying to add empty list item with eventID $event.")
                this.items = this.items.plus(item.toItem())
            }
            is ListItemCompletedEvent -> {
                val item = getItem(event.itemID) ?: throw RuntimeException("TODO")
                item.complete()
            }
        }
    }

    fun getItem(itemID: String): Item? {
        return items.find { it.id == itemID }
    }
}

fun NewListNote(): ListNote {
    return ListNote()
}