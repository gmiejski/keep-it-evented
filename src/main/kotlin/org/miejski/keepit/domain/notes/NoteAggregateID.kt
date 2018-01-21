package org.miejski.keepit.domain.notes

data class NoteAggregateID(val customerID: String, val aggName: String

) {
    override fun toString(): String {
        return "$customerID:$aggName"
    }
}

fun NotesAggregateID(customerID: String): NoteAggregateID {
    return NoteAggregateID(customerID, "NoteAggregate")
}