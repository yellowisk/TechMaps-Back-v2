CREATE TABLE techmaps_platform.file_metadata (
    id uuid NOT NULL PRIMARY KEY,
    creator_id uuid NOT NULL REFERENCES techmaps_platform.user(id) ON DELETE CASCADE
    file_name varchar NOT NULL,
    original_file_name varchar NOT NULL,
    content_type varchar NOT NULL,
    size bigint NOT NULL,
    storage_type varchar NOT NULL,
    path varchar NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
);

ALTER TABLE techmaps_platform.file_metadata OWNER TO "techmaps";