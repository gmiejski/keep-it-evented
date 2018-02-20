package org.miejski.keepit.domain.listNotes.repository

import org.miejski.keepit.domain.common.commands.Command
import org.miejski.keepit.domain.listNotes.ListNote

interface ListNotesAggregateRepository {
    fun get(user: String, noteID: String): ListNote?
    fun getAll(user: String): List<ListNote>
    fun update(user: String, note: ListNote, command: Command): ListNote
}

