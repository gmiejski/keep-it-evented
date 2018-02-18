package org.miejski.keepit.domain.notes.events

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer

data class NoteEditedEvent(
    @field:TaggedFieldSerializer.Tag(1) private val noteID: String = "",
    @field:TaggedFieldSerializer.Tag(2) val newContent: String = "") : BasicEvent() {

    override fun targetAggID(): String {
        return noteID
    }
}