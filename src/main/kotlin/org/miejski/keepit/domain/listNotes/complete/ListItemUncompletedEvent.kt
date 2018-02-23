package org.miejski.keepit.domain.listNotes.complete

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import org.miejski.keepit.domain.common.events.BasicEvent

class ListItemUncompletedEvent(@field:TaggedFieldSerializer.Tag(1) private val noteID: String = "",
                               @field:TaggedFieldSerializer.Tag(2) val itemID: String = "") : BasicEvent() {
    override fun targetAggID(): String {
        return noteID
    }
}