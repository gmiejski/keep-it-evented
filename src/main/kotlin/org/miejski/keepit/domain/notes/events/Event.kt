package org.miejski.keepit.domain.notes.events

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import java.time.ZonedDateTime

interface Event {
    fun targetAggID(): String
    fun eventTime(): ZonedDateTime
}

