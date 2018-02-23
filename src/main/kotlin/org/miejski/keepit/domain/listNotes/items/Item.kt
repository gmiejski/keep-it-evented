package org.miejski.keepit.domain.listNotes.items

import org.miejski.keepit.domain.listNotes.ListItemDTO

data class Item(val id: String, val content: String) {

    var isCompleted: Boolean = false
        private set

    fun toDto(): ListItemDTO {
        return ListItemDTO(id, content, isCompleted)
    }

    fun complete() {
        if (isCompleted) {
            throw RuntimeException("Item $id is already completed!")
        }
        isCompleted = true
    }

    fun uncomplete() {
        if (!isCompleted) {
            throw RuntimeException("Item $id is already uncompleted!")
        }
        isCompleted = false
    }
}