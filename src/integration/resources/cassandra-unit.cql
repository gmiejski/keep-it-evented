
CREATE TABLE notes_events (
    row_id varchar,
    agg_id varchar,
    event_time tuple<timestamp,varchar>,
    event_blob blob,
    event_mapper varchar,
    PRIMARY KEY ((row_id), agg_id, event_time),
);