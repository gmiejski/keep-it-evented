package org.miejski.keepit.domain.listNotes.items

import org.miejski.keepit.domain.common.commands.Command

data class AddListItemCommand(val noteID: String, val content: String) : Command