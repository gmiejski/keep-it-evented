package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.AggregateNameID
import org.miejski.keepit.domain.aggregateNameID

const val NOTES_AGGREGATE_NAME = "NoteAggregate"

fun CreateNotesAggregateID(customerID: String): AggregateNameID {
    return aggregateNameID(customerID, NOTES_AGGREGATE_NAME)
}