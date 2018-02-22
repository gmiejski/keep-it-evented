package org.miejski.keepit.domain.listNotes.items

import org.miejski.keepit.domain.listNotes.ListItemDTO

data class Item(val id: String, val content: String) {

    private var isCompleted: Boolean = false

    fun toDto(): ListItemDTO {
        return ListItemDTO(id, content, isCompleted)
    }

    fun complete() {
        isCompleted = true
    }
}