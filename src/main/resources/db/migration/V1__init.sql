CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    id                 UUID               DEFAULT uuid_generate_v4() PRIMARY KEY,
    name               TEXT      NOT NULL,
    surname            TEXT      NOT NULL,
    email_address      TEXT      NOT NULL,
--  Required columns for auditing.
    created_by         TEXT      NOT NULL,
    created_date       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_by   TEXT      NOT NULL,
    last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
