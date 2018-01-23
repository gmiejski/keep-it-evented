package org.miejski.keepit.domain.notes.events

interface Event {
    fun ID(): String // TODO split into ID() - event one and TargetAgID() to know what aggregate it targets
}