package org.miejski.keepit.domain.notes.events

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import java.time.ZonedDateTime

abstract class BasicEvent : Event {
    @field:TaggedFieldSerializer.Tag(0)
    val eventTime = ZonedDateTime.now()

    override fun eventTime(): ZonedDateTime {
        return eventTime

    }
}