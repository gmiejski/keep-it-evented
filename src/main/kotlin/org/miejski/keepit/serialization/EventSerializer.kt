package org.miejski.keepit.serialization

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import org.miejski.keepit.domain.notes.events.Event
import org.miejski.keepit.domain.notes.events.NoteCreatedEvent
import org.miejski.keepit.domain.notes.events.NoteEditedEvent
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import kotlin.concurrent.getOrSet

class EventSerializer {

    val myThreadLocal = ThreadLocal<Kryo>()

    val classByName = mapOf(
        Pair(NoteCreatedEvent::class.java.simpleName, NoteCreatedEvent::class.javaObjectType),
        Pair(NoteEditedEvent::class.java.simpleName, NoteEditedEvent::class.javaObjectType)
    )

    fun serialize(domainEvent: Event): Pair<ByteBuffer, String> {
        val output = Output(ByteArrayOutputStream())
        getKryo().writeObject(output, domainEvent)
        return Pair(ByteBuffer.wrap(output.buffer), domainEvent::class.java.simpleName)
    }

    fun deserialize(bytes: ByteArray, clazz: String): Event {
        val input = Input(bytes)
        return getKryo().readObject(input, classByName[clazz])
    }

    private fun getKryo() = myThreadLocal.getOrSet {
        val kryo = Kryo()
        kryo.register(NoteCreatedEvent::class.java, TaggedFieldSerializer<NoteCreatedEvent>(kryo, NoteCreatedEvent::class.javaObjectType))
        kryo.register(NoteEditedEvent::class.java, TaggedFieldSerializer<NoteEditedEvent>(kryo, NoteEditedEvent::class.javaObjectType))
        return kryo
    }
}

