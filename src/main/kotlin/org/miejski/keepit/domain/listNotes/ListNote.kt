package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.Aggregate
import org.miejski.keepit.domain.common.events.Event


class ListNote : Aggregate<ListNote> {
    override fun ID(): String {
        TODO("not implemented")
    }

    fun toDto(): ListNoteDTO {
        TODO("not implemented")
    }

    override fun applyEvent(event: Event): ListNote {
        TODO("not implemented")
    }
}

fun NewListNote(): ListNote {
    return ListNote()
}