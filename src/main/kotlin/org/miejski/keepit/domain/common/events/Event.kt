package org.miejski.keepit.domain.common.events

import java.time.ZonedDateTime

interface Event {
    fun targetAggID(): String
    fun eventTime(): ZonedDateTime
}

