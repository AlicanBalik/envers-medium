CREATE SEQUENCE hibernate_sequence;

CREATE TABLE revinfo
(
    rev      INTEGER PRIMARY KEY NOT NULL, -- Version number.
    revtstmp BIGINT                        -- Epoch timestamp of the version number.
);

CREATE TABLE users_aud
(
    id                 UUID,
    rev                INTEGER REFERENCES revinfo (rev), -- The version number of the entity.
    revend             INTEGER REFERENCES revinfo (rev), -- The version of the next version number after entity gets updated.
    revtype            SMALLINT,                         -- The type of the revision.
    revend_tstmp       TIMESTAMP,                        -- The timestamp of the next version number after entity gets updated.
    name               TEXT,
    surname            TEXT,
    email_address      TEXT,
    last_modified_by   TEXT,
    last_modified_date TIMESTAMP,
    PRIMARY KEY (id, rev)
);