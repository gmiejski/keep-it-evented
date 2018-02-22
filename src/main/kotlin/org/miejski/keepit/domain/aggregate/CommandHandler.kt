package org.miejski.keepit.domain.aggregate

import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.common.events.Event

interface CommandHandler<T> {
    fun applyCommand(aggregate: T, command: Command): List<Event>
}