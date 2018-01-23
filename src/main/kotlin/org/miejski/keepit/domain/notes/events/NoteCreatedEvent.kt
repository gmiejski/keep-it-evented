package org.miejski.keepit.domain.notes.events

data class NoteCreatedEvent(private val noteId: String="", val content: String="") : Event {
    override fun ID(): String {
        return noteId
    }
}