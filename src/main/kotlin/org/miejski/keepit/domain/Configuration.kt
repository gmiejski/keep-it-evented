package org.miejski.keepit.domain

import org.miejski.keepit.domain.aggregate.EventSourcedRepository
import org.miejski.keepit.domain.aggregate.EventsHandler
import org.miejski.keepit.domain.listNotes.ListNotesCommandHandler
import org.miejski.keepit.domain.listNotes.ListNotesService
import org.miejski.keepit.domain.listNotes.NewListNote
import org.miejski.keepit.domain.listNotes.complete.ListItemCompletedEvent
import org.miejski.keepit.domain.listNotes.create.ListNoteCreatedEvent
import org.miejski.keepit.domain.listNotes.items.ListItemAddedEvent
import org.miejski.keepit.domain.notes.NewNote
import org.miejski.keepit.domain.notes.NotesCommandHandler
import org.miejski.keepit.domain.notes.NotesService
import org.miejski.keepit.domain.notes.archive.NoteArchivedEvent
import org.miejski.keepit.domain.notes.create.NoteCreatedEvent
import org.miejski.keepit.domain.notes.edit.NoteEditedEvent
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
        val notesRepository = EventSourcedRepository(NotesCommandHandler(), EventsHandler(), eventStore, { NewNote() })
        val listNotesRepository = EventSourcedRepository(ListNotesCommandHandler(), EventsHandler(), eventStore, { NewListNote() })
        return NotesFacade(NotesService(notesRepository), ListNotesService(listNotesRepository))
    }

    @Bean
    @Profile("integration")
    fun integrationNotesFacade(noteEventAccessor: NoteEventAccessor): NotesFacade {
        val cassandraEventStore = CassandraEventStore(noteEventAccessor, eventSerializer())
        val notesRepository = EventSourcedRepository(NotesCommandHandler(), EventsHandler(), cassandraEventStore, { NewNote() })
        val listNotesRepository = EventSourcedRepository(ListNotesCommandHandler(), EventsHandler(), cassandraEventStore, { NewListNote() })
        return NotesFacade(NotesService(notesRepository), ListNotesService(listNotesRepository))
    }

    @Bean
    fun eventSerializer(): EventSerializer {
        return EventSerializer(listOf(
            NoteCreatedEvent::class.java,
            NoteEditedEvent::class.java,
            NoteArchivedEvent::class.java,
            ListNoteCreatedEvent::class.java,
            ListItemAddedEvent::class.java,
            ListItemCompletedEvent::class.java
        ))
    }
}