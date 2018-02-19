package org.miejski.keepit.domain.notes.archive

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import org.miejski.keepit.domain.common.events.BasicEvent

data class NoteArchivedEvent(@field:TaggedFieldSerializer.Tag(1) private val noteID: String = "") : BasicEvent() {
    override fun targetAggID(): String {
        return noteID
    }
}