package org.miejski.keepit.domain.listNotes.create

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import org.miejski.keepit.domain.common.events.BasicEvent

data class ListNoteCreatedEvent(
    @field:TaggedFieldSerializer.Tag(1) private val noteID: String = "",
    @field:TaggedFieldSerializer.Tag(2) val content: List<String> = emptyList()) : BasicEvent() {

    override fun targetAggID(): String {
        return noteID
    }
}