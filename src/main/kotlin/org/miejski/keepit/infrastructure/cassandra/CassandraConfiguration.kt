package org.miejski.keepit.infrastructure.cassandra

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session
import com.datastax.driver.mapping.MappingManager
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
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
        return cluster.connect(cassandraProperties.keyspaceName)
    }

    @Bean
    fun notesAccessor(session: Session): NoteEventAccessor {
        val manager = MappingManager(session)
        return manager.createAccessor(NoteEventAccessor::class.java)
    }
}