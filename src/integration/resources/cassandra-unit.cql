
CREATE TABLE users (
  user_name varchar PRIMARY KEY,
);

INSERT INTO users (user_name)
  VALUES ('johndoe');


CREATE TABLE notes_events (
    row_id varchar,
    agg_id varchar,
    event_blob blob,
    PRIMARY KEY (row_id, agg_id),
);