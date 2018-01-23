package org.miejski.keepit.domain.notes

data class UserNotesAggregateID(val customerID: String, val aggName: String

) {
    override fun toString(): String {
        return "$customerID:$aggName"
    }
}

fun CreateNotesAggregateID(customerID: String): UserNotesAggregateID {
    return UserNotesAggregateID(customerID, "NoteAggregate")
}