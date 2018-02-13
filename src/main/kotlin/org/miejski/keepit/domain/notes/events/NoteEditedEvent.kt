package org.miejski.keepit.domain.notes.events

import java.time.ZonedDateTime

data class NoteEditedEvent(private val noteID: String="", val newContent: String="") : Event {

    val eventTime = ZonedDateTime.now()

    override fun eventTime(): ZonedDateTime {
        return eventTime
    }

    override fun targetAggID(): String {
        return noteID
    }
}