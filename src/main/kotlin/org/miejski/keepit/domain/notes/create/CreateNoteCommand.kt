package org.miejski.keepit.domain.notes.create

import org.miejski.keepit.domain.common.commands.Command

data class CreateNoteCommand(val content: String) : Command