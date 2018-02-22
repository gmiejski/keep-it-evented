package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.aggregate.AggregateNameID
import org.miejski.keepit.domain.aggregate.aggregateNameID

const val NOTES_AGGREGATE_NAME = "NoteAggregate"

fun CreateNotesAggregateID(customerID: String): AggregateNameID {
    return aggregateNameID(customerID, NOTES_AGGREGATE_NAME)
}