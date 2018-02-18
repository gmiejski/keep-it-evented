package org.miejski.keepit.infrastructure.cassandra

import org.miejski.keepit.domain.notes.events.NoteCreatedEvent
import org.miejski.keepit.domain.notes.events.NoteEditedEvent
import org.miejski.keepit.serialization.EventSerializer
import spock.lang.Specification

class EventSerializerSpec extends Specification {

    def serializer = new EventSerializer()

    def "serialize and deserialize note created event"() {
        given:
        def event = new NoteCreatedEvent("1", "content")

        when:
        def serialize = serializer.serialize(event)

        then:
        serialize.second == event.class.simpleName
        def deserialize = serializer.deserialize(serialize.first.array(), serialize.second)
        deserialize == event
    }

    def "serialize and deserialize note edited event"() {
        given:
        def event = new NoteEditedEvent("1", "new one")

        when:
        def serialize = serializer.serialize(event)

        then:
        serialize.second == event.class.simpleName
        def deserialize = serializer.deserialize(serialize.first.array(), serialize.second)
        deserialize == event
    }


}


