package org.miejski.keepit.domain

import org.miejski.keepit.domain.listNotes.ListNotesService
import org.miejski.keepit.domain.listNotes.repository.ListNotesCommandHandler
import org.miejski.keepit.domain.listNotes.repository.ListNotesRepository
import org.miejski.keepit.domain.notes.NotesFacade
import org.miejski.keepit.domain.notes.NotesService
import org.miejski.keepit.domain.notes.repository.NotesCommandHandler
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
        val eventStore = InMemoryEventStore()
        val notesRepository = NotesRepository(NotesCommandHandler(), EventsHandler(), eventStore)
        val listNotesRepository = ListNotesRepository(ListNotesCommandHandler(), EventsHandler(), eventStore)
        return NotesFacade(NotesService(notesRepository), ListNotesService(listNotesRepository))
    }

    @Bean
    @Profile("integration")
    fun integrationNotesFacade(noteEventAccessor: NoteEventAccessor): NotesFacade {
        val cassandraEventStore = CassandraEventStore(noteEventAccessor, eventSerializer())
        val notesRepository = NotesRepository(NotesCommandHandler(), EventsHandler(), cassandraEventStore)
        val listNotesRepository = ListNotesRepository(ListNotesCommandHandler(), EventsHandler(), cassandraEventStore)
        return NotesFacade(NotesService(notesRepository), ListNotesService(listNotesRepository))
    }

    @Bean
    fun eventSerializer(): EventSerializer {
        return EventSerializer()
    }
}