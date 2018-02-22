package org.miejski.keepit.domain.listNotes.items

import org.miejski.keepit.domain.listNotes.ListItemDTO

data class Item(val id: String, val content: String) {

    fun toDto(): ListItemDTO {
        return ListItemDTO(id, content)
    }
}