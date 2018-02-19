package org.miejski.keepit.domain.notes.archive

import org.miejski.keepit.domain.common.commands.Command

data class ArchiveNoteCommand(val noteID: String) : Command