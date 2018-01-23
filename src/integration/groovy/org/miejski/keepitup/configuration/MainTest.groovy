package org.miejski.keepitup.configuration

import com.datastax.driver.core.Session
import org.cassandraunit.CQLDataLoader
import org.cassandraunit.CassandraCQLUnit
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet
import org.junit.Rule
import org.miejski.keepit.KeepItApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(classes = KeepItApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class MainTest extends Specification {

    @Autowired
    CassandraProperties cassandraProperties

    @Rule
    CassandraCQLUnit testRule = new CassandraCQLUnit(
            new ClassPathCQLDataSet("cassandra-unit.cql", "notes"))

    @LocalServerPort
    int port

    @Autowired
    Session session

    def localhost() {
        return "http://localhost:$port".toString()
    }

    void setupSpec() {
    }

    void cleanupSpec() {
    }
}
