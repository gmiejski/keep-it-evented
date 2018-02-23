package org.miejski.keepit.infrastructure.eventstore.cassandra

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.DataType
import com.datastax.driver.core.Session
import com.datastax.driver.extras.codecs.jdk8.ZonedDateTimeCodec
import com.datastax.driver.mapping.MappingManager
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Configuration
class CassandraConfiguration constructor(val cassandraProperties: CassandraProperties) {

    @Bean
    @Profile("integration")
    fun integrationSession(): Session {
        val cluster = Cluster.builder()
            .withClusterName(cassandraProperties.clusterName)
            .addContactPoint(cassandraProperties.contactPoints)
            .withPort(9142)
            .build()

        val tupleType = cluster.metadata
            .newTupleType(DataType.timestamp(), DataType.varchar())
        cluster.configuration.codecRegistry
            .register(ZonedDateTimeCodec(tupleType))

        return cluster.connect(cassandraProperties.keyspaceName)
    }

    @Bean
    fun notesAccessor(session: Session): NoteEventAccessor {
        val manager = MappingManager(session)
        return manager.createAccessor(NoteEventAccessor::class.java)
    }
}