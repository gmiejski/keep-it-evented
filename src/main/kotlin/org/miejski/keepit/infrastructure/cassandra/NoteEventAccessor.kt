package org.miejski.keepit.infrastructure.cassandra

import com.datastax.driver.mapping.Result
import com.datastax.driver.mapping.annotations.Accessor
import com.datastax.driver.mapping.annotations.Param
import com.datastax.driver.mapping.annotations.Query
import java.nio.ByteBuffer
import java.time.ZonedDateTime

@Accessor
interface NoteEventAccessor {

    @Query("SELECT * FROM notes_events where row_id=:row_id")
    fun getAll(@Param("row_id") rowId: String): Result<CassandraEventRow>

    @Query("SELECT * FROM notes_events where row_id=:row_id and agg_id=:agg_id")
    fun get(@Param("row_id") rowId: String, @Param("agg_id") noteID: String): Result<CassandraEventRow>

    @Query("insert into notes_events (row_id, agg_id, event_time, event_blob, event_mapper) values (?, ?, ?, ?, ?)")
    fun save(rowId: String, aggId: String, eventTime: ZonedDateTime, event: ByteBuffer, serializedUsed: String): Result<CassandraEventRow>

}