package org.miejski.keepit.domain

import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.common.events.Event

interface CommandHandler<T> {
    fun applyCommand(user: String, aggregate: T, command: Command): List<Event>
}