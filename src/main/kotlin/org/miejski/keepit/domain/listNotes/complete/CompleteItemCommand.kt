package org.miejski.keepit.domain.listNotes.complete

import org.miejski.keepit.domain.common.commands.Command

data class CompleteItemCommand(val noteID: String, val itemID: String) : Command