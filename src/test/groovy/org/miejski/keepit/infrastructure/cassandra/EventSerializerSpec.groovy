package org.miejski.keepit.infrastructure.cassandra

import org.miejski.keepit.domain.notes.events.NoteCreatedEvent
import spock.lang.Specification

class EventSerializerSpec extends Specification {

    def serializer = new EventSerializer()

    def "serialize and deserialize example event"() {
        given:
        def event = new NoteCreatedEvent("1", "content")

        when:
        def serialize = serializer.serialize(event)

        then:
        def deserialize = serializer.deserialize(serialize.array())
        deserialize == event
    }
}


