package org.miejski.keepit.infrastructure.cassandra

import com.datastax.driver.mapping.annotations.ClusteringColumn
import com.datastax.driver.mapping.annotations.Column
import com.datastax.driver.mapping.annotations.PartitionKey
import com.datastax.driver.mapping.annotations.Table
import java.nio.ByteBuffer
import java.time.ZonedDateTime

@Table(keyspace = "notes", name = "notes_events")
class CassandraEventRow {
    @PartitionKey
    @Column(name = "row_id")
    lateinit var rowId: String

    @ClusteringColumn(0)
    @Column(name = "agg_id")
    lateinit var aggId: String

    @ClusteringColumn(1)
    @Column(name = "event_time")
    lateinit var eventTime: ZonedDateTime

    @Column(name = "event_blob")
    lateinit var eventBlob: ByteBuffer

    @Column(name = "event_mapper")
    lateinit var eventMapper: String
}