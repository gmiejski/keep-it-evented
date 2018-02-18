package org.miejski.keepit.domain.notes.events

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import java.time.ZonedDateTime

data class NoteCreatedEvent(
    @field:TaggedFieldSerializer.Tag(0) private val noteID: String = "",
    @field:TaggedFieldSerializer.Tag(1) val content: String = "") : Event {

    @field:TaggedFieldSerializer.Tag(2)
    val eventTime = ZonedDateTime.now()

    override fun eventTime(): ZonedDateTime {
        return eventTime
    }

    override fun targetAggID(): String {
        return noteID
    }
}