package org.miejski.keepit.domain.notes.events

import java.time.ZonedDateTime

interface Event {
    fun targetAggID(): String
    fun eventTime(): ZonedDateTime
}