package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.notes.commands.NotesCommandHandler
import org.miejski.keepit.domain.notes.repository.NotesRepository
import org.miejski.keepit.infrastructure.cassandra.eventstore.InMemoryEventStore

class Configuration {
    companion object {
        @JvmStatic fun testNotesFacade(): NotesFacade {
            return NotesFacade(NotesService(NotesRepository(NotesCommandHandler(), InMemoryEventStore())))
        }
    }
}