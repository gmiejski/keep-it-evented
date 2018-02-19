package org.miejski.keepit.domain.notes.edit

import org.miejski.keepit.domain.common.commands.Command

data class EditNoteCommand(val noteID: String, val content: String) : Command