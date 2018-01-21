package org.miejski.keepit.domain

import org.miejski.keepit.domain.notes.NoteAggregateID

interface Aggregate { // TODO make use

    fun ID(): NoteAggregateID
}