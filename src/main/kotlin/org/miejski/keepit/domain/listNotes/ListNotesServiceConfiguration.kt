package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.aggregate.EventSourcedRepository
import org.miejski.keepit.domain.aggregate.EventStore
import org.miejski.keepit.domain.aggregate.EventsHandler
import org.miejski.keepit.infrastructure.eventstore.cassandra.CassandraEventStore
import org.miejski.keepit.infrastructure.eventstore.cassandra.NoteEventAccessor
import org.miejski.keepit.serialization.EventSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ListNotesServiceConfiguration {
    fun testListNotesService(eventStore: EventStore): ListNotesService {
        val listNotesRepository = EventSourcedRepository(ListNotesCommandHandler(), EventsHandler(), eventStore, { NewListNote() })
        return ListNotesService(listNotesRepository)
    }

    @Bean
    @Profile("integration")
    fun integrationListNotesService(noteEventAccessor: NoteEventAccessor,
                                    eventSerializer: EventSerializer): ListNotesService {
        val cassandraEventStore = CassandraEventStore(noteEventAccessor, eventSerializer)
        val listNotesRepository = EventSourcedRepository(ListNotesCommandHandler(), EventsHandler(), cassandraEventStore, { NewListNote() })
        return ListNotesService(listNotesRepository)
    }
}