package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.notes.commands.NotesCommandHandler
import org.miejski.keepit.domain.notes.events.NotesEventsHandler
import org.miejski.keepit.domain.notes.repository.NotesRepository
import org.miejski.keepit.infrastructure.cassandra.CassandraEventStore
import org.miejski.keepit.infrastructure.eventstore.InMemoryEventStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class Configuration {

    fun testNotesFacade(): NotesFacade {
        val notesRepository = NotesRepository(NotesCommandHandler(), NotesEventsHandler(), InMemoryEventStore())
        return NotesFacade(NotesService(notesRepository))
    }

    @Bean
    @Profile("integration")
    fun integrationNotesFacade(): NotesFacade {
//        val notesRepository = NotesRepository(NotesCommandHandler(), NotesEventsHandler(), CassandraEventStore()) // TODO
        val notesRepository = NotesRepository(NotesCommandHandler(), NotesEventsHandler(), InMemoryEventStore())
        return NotesFacade(NotesService(notesRepository))
    }
}