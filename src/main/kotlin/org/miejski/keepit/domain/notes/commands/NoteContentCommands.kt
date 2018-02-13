package org.miejski.keepit.domain.notes.commands

data class CreateNoteCommand(val content: String) : Command

data class EditNoteCommand(val noteID: String, val content: String) : Command