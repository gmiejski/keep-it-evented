package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.Aggregate
import org.miejski.keepit.domain.common.events.Event

class ListNote : Aggregate{
    override fun ID(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun toDto(): ListNoteDTO {
        TODO("not implemented")
    }

    fun applyEvent(event: Event) {

    }
}

fun NewListNote() : ListNote {
    return ListNote()
}