package org.miejski.keepit.serialization

import org.miejski.keepit.domain.listNotes.complete.ListItemCompletedEvent
import org.miejski.keepit.domain.listNotes.complete.ListItemUncompletedEvent
import org.miejski.keepit.domain.listNotes.create.ListNoteCreatedEvent
import org.miejski.keepit.domain.listNotes.items.ListItemAddedEvent
import org.miejski.keepit.domain.notes.archive.NoteArchivedEvent
import org.miejski.keepit.domain.notes.create.NoteCreatedEvent
import org.miejski.keepit.domain.notes.edit.NoteEditedEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {
    @Bean
    fun eventSerializer(): EventSerializer {
        return EventSerializer(listOf(
            NoteCreatedEvent::class.java,
            NoteEditedEvent::class.java,
            NoteArchivedEvent::class.java,
            ListNoteCreatedEvent::class.java,
            ListItemAddedEvent::class.java,
            ListItemCompletedEvent::class.java,
            ListItemUncompletedEvent::class.java
        ))
    }

}