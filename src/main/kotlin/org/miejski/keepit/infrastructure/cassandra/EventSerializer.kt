package org.miejski.keepit.infrastructure.cassandra

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.events.NoteCreatedEvent
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer


class EventSerializer {

    val kryo = Kryo()

    fun serialize(domainEvent: Event): ByteBuffer {
        val output = Output(ByteArrayOutputStream())
        kryo.writeObject(output, domainEvent)
        return ByteBuffer.wrap(output.buffer)
    }

    fun deserialize(bytes: ByteArray): Event {
        val input = Input(bytes)
        return kryo.readObject(input, NoteCreatedEvent::class.java)
    }
}