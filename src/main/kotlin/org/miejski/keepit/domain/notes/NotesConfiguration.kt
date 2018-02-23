package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.aggregate.EventSourcedRepository
import org.miejski.keepit.domain.aggregate.EventsHandler
import org.miejski.keepit.infrastructure.eventstore.InMemoryEventStore
import org.miejski.keepit.infrastructure.eventstore.cassandra.CassandraEventStore
import org.miejski.keepit.infrastructure.eventstore.cassandra.NoteEventAccessor
import org.miejski.keepit.serialization.EventSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class NotesConfiguration {

    fun testNotesService(): NotesService {
        val eventStore = InMemoryEventStore()
        val notesRepository = EventSourcedRepository(NotesCommandHandler(), EventsHandler(), eventStore, { NewNote() })
        return NotesService(notesRepository)
    }

    @Bean
    @Profile("integration")
    fun integrationNotesService(noteEventAccessor: NoteEventAccessor,
                                eventSerializer: EventSerializer): NotesService {
        val cassandraEventStore = CassandraEventStore(noteEventAccessor, eventSerializer)
        val notesRepository = EventSourcedRepository(NotesCommandHandler(), EventsHandler(), cassandraEventStore, { NewNote() })
        return NotesService(notesRepository)
    }
}