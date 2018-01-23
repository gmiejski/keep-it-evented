
CREATE TABLE notes_events (
    row_id varchar,
    agg_id varchar,
    event_blob blob,
    PRIMARY KEY (row_id, agg_id),
);