package org.miejski.keepit.domain.notes.edit

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import org.miejski.keepit.domain.common.events.BasicEvent

data class NoteEditedEvent(
    @field:TaggedFieldSerializer.Tag(1) private val noteID: String = "",
    @field:TaggedFieldSerializer.Tag(2) val newContent: String = "") : BasicEvent() {

    override fun targetAggID(): String {
        return noteID
    }
}