\c techmaps techmaps-app

DROP SCHEMA IF EXISTS techmaps_platform CASCADE;

CREATE SCHEMA techmaps_platform;

ALTER SCHEMA techmaps_platform OWNER TO "techmaps";

DROP TYPE IF EXISTS techmaps_platform.user_position CASCADE;

CREATE TYPE techmaps_platform.position AS ENUM (
    'ADMIN',
    'EDUCATIONAL',
    'STUDENT'
);

DROP TABLE IF EXISTS techmaps_platform.user CASCADE;

CREATE TABLE techmaps_platform.user (
    id uuid NOT NULL PRIMARY KEY,
    email varchar NOT NULL UNIQUE,
    position techmaps_platform.position NOT NULL,
    username varchar NOT NULL UNIQUE,
    password varchar NOT NULL
);

ALTER TABLE techmaps_platform.user OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.system_invite CASCADE;

CREATE TABLE techmaps_platform.system_invite (
    id uuid NOT NULL PRIMARY KEY,
    email varchar NOT NULL UNIQUE,
    code varchar NOT NULL UNIQUE,
    position techmaps_platform.position NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL
);

ALTER TABLE techmaps_platform.system_invite ADD CONSTRAINT check_expires_at CHECK (expires_at > created_at);

ALTER TABLE techmaps_platform.system_invite OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.dashboard CASCADE;

CREATE TABLE techmaps_platform.dashboard(
    id uuid NOT NULL PRIMARY KEY,
    user_id uuid NOT NULL REFERENCES techmaps_platform.user(id) ON DELETE CASCADE,
    total_roadmaps int NOT NULL
);

ALTER TABLE techmaps_platform.dashboard OWNER TO "techmaps";

DROP TYPE IF EXISTS techmaps_platform.language CASCADE;

CREATE TYPE techmaps_platform.language AS ENUM (
    'PYTHON'
);

ALTER TYPE techmaps_platform.language OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.roadmap CASCADE;

CREATE TABLE techmaps_platform.roadmap (
    id uuid NOT NULL PRIMARY KEY,
    name varchar NOT NULL,
    language techmaps_platform.language NOT NULL
);

ALTER TABLE techmaps_platform.roadmap OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.roadmap_user CASCADE;

CREATE TABLE techmaps_platform.roadmap_user (
    id uuid NOT NULL PRIMARY KEY,
    roadmap_id uuid NOT NULL REFERENCES techmaps_platform.roadmap(id) ON DELETE CASCADE,
    user_id uuid NOT NULL REFERENCES techmaps_platform.user(id) ON DELETE CASCADE,
    is_done BOOLEAN NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP
);

ALTER TABLE techmaps_platform.roadmap_user OWNER TO "techmaps";

DROP TYPE IF EXISTS techmaps_platform.role CASCADE;

CREATE TYPE techmaps_platform.role AS ENUM (
    'TEACHER',
    'ASSISTANT'
);

DROP TABLE IF EXISTS techmaps_platform.group CASCADE;

CREATE TABLE techmaps_platform.group (
    id uuid NOT NULL PRIMARY KEY,
    parent_id uuid REFERENCES techmaps_platform.group(id) ON DELETE CASCADE,
    creator_id uuid REFERENCES techmaps_platform.user(id) ON DELETE SET NULL,
    name varchar NOT NULL
);

ALTER TABLE techmaps_platform.group OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.group_invite CASCADE;

CREATE TABLE techmaps_platform.group_invite (
    id uuid NOT NULL PRIMARY KEY,
    group_id uuid NOT NULL REFERENCES techmaps_platform.group(id) ON DELETE CASCADE,
    email varchar NOT NULL,
    code varchar NOT NULL UNIQUE,
    role techmaps_platform.role,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL
);

ALTER TABLE techmaps_platform.group_invite ADD CONSTRAINT check_group_invite_expires_at CHECK (expires_at > created_at);

ALTER TABLE techmaps_platform.group_invite OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.group_user CASCADE;

CREATE TABLE techmaps_platform.group_user (
    id uuid NOT NULL PRIMARY KEY,
    group_id uuid NOT NULL REFERENCES techmaps_platform.group(id) ON DELETE CASCADE,
    user_id uuid NOT NULL REFERENCES techmaps_platform.user(id) ON DELETE CASCADE,
    role techmaps_platform.role
);

ALTER TABLE techmaps_platform.group_user OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.group_roadmap CASCADE;

CREATE TABLE techmaps_platform.group_roadmap (
    id uuid NOT NULL PRIMARY KEY,
    group_id uuid NOT NULL REFERENCES techmaps_platform.group(id) ON DELETE CASCADE,
    roadmap_id uuid NOT NULL REFERENCES techmaps_platform.roadmap(id) ON DELETE CASCADE
);

ALTER TABLE techmaps_platform.group_roadmap OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.group_post CASCADE;

CREATE TABLE techmaps_platform.group_post (
    id uuid NOT NULL PRIMARY KEY,
    group_id uuid NOT NULL REFERENCES techmaps_platform.group(id) ON DELETE CASCADE,
    user_id uuid NOT NULL REFERENCES techmaps_platform.user(id) ON DELETE CASCADE,
    title varchar NOT NULL,
    text varchar NOT NULL
);

ALTER TABLE techmaps_platform.group_post OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.group_comment CASCADE;

CREATE TABLE techmaps_platform.group_comment (
    id uuid NOT NULL PRIMARY KEY,
    group_post_id uuid NOT NULL REFERENCES techmaps_platform.group_post(id) ON DELETE CASCADE,
    user_id uuid NOT NULL REFERENCES techmaps_platform.user(id) ON DELETE CASCADE,
    text varchar NOT NULL
);

ALTER TABLE techmaps_platform.group_comment OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.stage CASCADE;

CREATE TABLE techmaps_platform.stage (
    id uuid NOT NULL PRIMARY KEY,
    roadmap_id uuid NOT NULL REFERENCES techmaps_platform.roadmap(id) ON DELETE CASCADE,
    name varchar NOT NULL,
    position INTEGER NOT NULL
);

ALTER TABLE techmaps_platform.stage OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.stage_user CASCADE;

CREATE TABLE techmaps_platform.stage_user (
    id uuid NOT NULL PRIMARY KEY,
    stage_id uuid NOT NULL REFERENCES techmaps_platform.stage(id) ON DELETE CASCADE,
    roadmap_user_id uuid NOT NULL REFERENCES techmaps_platform.roadmap_user(id) ON DELETE CASCADE,
    is_done BOOLEAN NOT NULL,
    position INTEGER NOT NULL
);

ALTER TABLE techmaps_platform.stage_user OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.task CASCADE;

CREATE TABLE techmaps_platform.task(
    id uuid NOT NULL PRIMARY KEY,
    stage_id uuid NOT NULL REFERENCES techmaps_platform.stage(id) ON DELETE CASCADE,
    title varchar NOT NULL,
    description varchar NOT NULL,
    position int NOT NULL
);

ALTER TABLE techmaps_platform.task OWNER TO "techmaps";

CREATE TABLE techmaps_platform.task_user (
    id uuid NOT NULL PRIMARY KEY,
    task_id uuid NOT NULL REFERENCES techmaps_platform.task(id) ON DELETE CASCADE,
    roadmap_user_id uuid NOT NULL REFERENCES techmaps_platform.roadmap_user(id) ON DELETE CASCADE,
    is_done BOOLEAN NOT NULL
);

ALTER TABLE techmaps_platform.task_user OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.step CASCADE;

CREATE TABLE techmaps_platform.step (
    id uuid NOT NULL PRIMARY KEY,
    task_id uuid NOT NULL REFERENCES techmaps_platform.task(id) ON DELETE CASCADE,
    position integer NOT NULL,
    text varchar NOT NULL,
    link varchar NOT NULL
);

ALTER TABLE techmaps_platform.step OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.step_user CASCADE;

CREATE TABLE techmaps_platform.step_user (
    id uuid NOT NULL PRIMARY KEY,
    step_id uuid NOT NULL REFERENCES techmaps_platform.step(id) ON DELETE CASCADE,
    roadmap_user_id uuid NOT NULL REFERENCES techmaps_platform.roadmap_user(id) ON DELETE CASCADE,
    is_done BOOLEAN NOT NULL
);

ALTER TABLE techmaps_platform.step_user OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.file_metadata CASCADE;

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