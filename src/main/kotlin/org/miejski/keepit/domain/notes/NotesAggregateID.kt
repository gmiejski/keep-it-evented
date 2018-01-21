package org.miejski.keepit.domain.notes

data class NotesAggregateID(val customerID: String, val aggName: String

) {
    override fun toString(): String {
        return "$customerID:$aggName"
    }
}

fun CreateNotesAggregateID(customerID: String): NotesAggregateID {
    return NotesAggregateID(customerID, "NoteAggregate")
}