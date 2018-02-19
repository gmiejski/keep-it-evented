package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.notes.repository.NotesCommandHandler
import org.miejski.keepit.domain.notes.repository.NotesEventsHandler
import org.miejski.keepit.domain.notes.repository.NotesRepository
import org.miejski.keepit.infrastructure.cassandra.CassandraEventStore
import org.miejski.keepit.infrastructure.cassandra.NoteEventAccessor
import org.miejski.keepit.infrastructure.eventstore.InMemoryEventStore
import org.miejski.keepit.serialization.EventSerializer
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
    fun integrationNotesFacade(noteEventAccessor: NoteEventAccessor): NotesFacade {
        val cassandraEventStore = CassandraEventStore(noteEventAccessor, eventSerializer())
        val notesRepository = NotesRepository(NotesCommandHandler(), NotesEventsHandler(), cassandraEventStore)
        return NotesFacade(NotesService(notesRepository))
    }

    @Bean
    fun eventSerializer(): EventSerializer {
        return EventSerializer()
    }
}