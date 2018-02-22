package org.miejski.keepit.serialization

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer
import org.miejski.keepit.domain.common.events.Event
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import kotlin.concurrent.getOrSet
import kotlin.reflect.KClass

class EventSerializer(private val events: List<Class<out Event>>) {

    val myThreadLocal = ThreadLocal<Kryo>()

    val classByName = events.classesByNames()

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
        events.forEach {
            kryo.register(it::class.java, TaggedFieldSerializer<Event>(kryo, it::class.javaObjectType))
        }
        return kryo
    }
}

private fun List<Class<out Event>>.classesByNames(): Map<String, Class<out Event>> {
    val pair = this.map { Pair(it.simpleName, it) }
    return mapOf(*pair.toTypedArray())
}

