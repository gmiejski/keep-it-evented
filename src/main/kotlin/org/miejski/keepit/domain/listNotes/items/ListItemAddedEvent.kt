package org.miejski.keepit.domain.listNotes.items

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import org.miejski.keepit.domain.common.events.BasicEvent
import org.miejski.keepit.domain.listNotes.create.ListNoteItem

data class ListItemAddedEvent(
    @field:TaggedFieldSerializer.Tag(1) private val noteID: String = "",
    @field:TaggedFieldSerializer.Tag(2) val listItem: ListNoteItem? = null
) : BasicEvent() {

    override fun targetAggID(): String {
        return noteID
    }
}