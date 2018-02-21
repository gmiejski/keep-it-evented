package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.aggregate.AggregateNameID
import org.miejski.keepit.domain.aggregate.aggregateNameID

const val LIST_NOTES_AGGREGATE_NAME = "ListNoteAggregate"

fun CreateListNotesAggregateID(customerID: String): AggregateNameID {
    return aggregateNameID(customerID, LIST_NOTES_AGGREGATE_NAME)
}