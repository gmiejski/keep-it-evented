package org.miejski.keepit.domain.listNotes.create

import org.miejski.keepit.domain.common.commands.Command

data class CreateListNoteCommand(val tasks: List<String>) : Command
