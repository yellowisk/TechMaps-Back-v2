DROP SCHEMA IF EXISTS techmaps_platform CASCADE;

CREATE SCHEMA techmaps_platform;

ALTER SCHEMA techmaps_platform OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.user CASCADE;

CREATE TABLE techmaps_platform.user (
    id uuid NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

ALTER TABLE techmaps_platform.user OWNER TO "techmaps";

ALTER TABLE techmaps_platform.user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);

DROP TABLE IF EXISTS techmaps_platform.dashboard CASCADE;

CREATE TABLE techmaps_platform.dashboard(
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    total_roadmaps int NOT NULL
);

ALTER TABLE techmaps_platform.dashboard OWNER TO "techmaps";

ALTER TABLE techmaps_platform.dashboard
    ADD CONSTRAINT dashboard_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.dashboard
    ADD CONSTRAINT dashboard_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES techmaps_platform.user(id) ON DELETE CASCADE;

DROP TYPE IF EXISTS techmaps_platform.language CASCADE;

CREATE TYPE techmaps_platform.language AS ENUM (
    'PYTHON'
);

ALTER TYPE techmaps_platform.language OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.roadmap CASCADE;

CREATE TABLE techmaps_platform.roadmap (
    id uuid NOT NULL,
    name VARCHAR(255) NOT NULL,
    language techmaps_platform.language NOT NULL
);

ALTER TABLE techmaps_platform.roadmap OWNER TO "techmaps";

ALTER TABLE techmaps_platform.roadmap
    ADD CONSTRAINT roadmap_pkey PRIMARY KEY (id);

DROP TABLE IF EXISTS techmaps_platform.roadmap_user CASCADE;

CREATE TABLE techmaps_platform.roadmap_user (
    id uuid NOT NULL,
    roadmap_id uuid NOT NULL,
    user_id uuid NOT NULL,
    is_done BOOLEAN NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP
);

ALTER TABLE techmaps_platform.roadmap_user OWNER TO "techmaps";

ALTER TABLE techmaps_platform.roadmap_user
    ADD CONSTRAINT roadmap_user_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.roadmap_user
    ADD CONSTRAINT roadmap_user_roadmap_id_fkey FOREIGN KEY (roadmap_id)
        REFERENCES techmaps_platform.roadmap(id) ON DELETE CASCADE;

ALTER TABLE techmaps_platform.roadmap_user
    ADD CONSTRAINT roadmap_user_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES techmaps_platform.user(id) ON DELETE CASCADE;

DROP TABLE IF EXISTS techmaps_platform.school CASCADE;

CREATE TABLE techmaps_platform.school (
    id uuid NOT NULL,
    name VARCHAR(255) NOT NULL
);

ALTER TABLE techmaps_platform.school OWNER TO "techmaps";

ALTER TABLE techmaps_platform.school
    ADD CONSTRAINT school_pkey PRIMARY KEY (id);

DROP TYPE IF EXISTS techmaps_platform.role CASCADE;

CREATE TYPE techmaps_platform.role AS ENUM (
    'OWNER',
    'COLLABORATOR',
    'STUDENT'
);

ALTER TYPE techmaps_platform.role OWNER TO "techmaps";

DROP TABLE IF EXISTS techmaps_platform.school_user CASCADE;

CREATE TABLE techmaps_platform.school_user (
    id uuid NOT NULL,
    school_id uuid NOT NULL,
    user_id uuid NOT NULL,
    role techmaps_platform.role NOT NULL
);

ALTER TABLE techmaps_platform.school_user OWNER TO "techmaps";

ALTER TABLE techmaps_platform.school_user
    ADD CONSTRAINT school_user_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.school_user
    ADD CONSTRAINT school_user_school_id_fkey FOREIGN KEY (school_id)
        REFERENCES techmaps_platform.school(id) ON DELETE CASCADE;

ALTER TABLE techmaps_platform.school_user
    ADD CONSTRAINT school_user_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES techmaps_platform.user(id) ON DELETE CASCADE;

DROP TABLE IF EXISTS techmaps_platform.school_roadmap CASCADE;

CREATE TABLE techmaps_platform.school_roadmap (
    id uuid NOT NULL,
    school_id uuid NOT NULL,
    roadmap_id uuid NOT NULL
);

ALTER TABLE techmaps_platform.school_roadmap OWNER TO "techmaps";

ALTER TABLE techmaps_platform.school_roadmap
    ADD CONSTRAINT school_roadmap_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.school_roadmap
    ADD CONSTRAINT school_roadmap_school_id_fkey FOREIGN KEY (school_id)
        REFERENCES techmaps_platform.school(id) ON DELETE CASCADE;

ALTER TABLE techmaps_platform.school_roadmap
    ADD CONSTRAINT school_roadmap_roadmap_id_fkey FOREIGN KEY (roadmap_id)
        REFERENCES techmaps_platform.roadmap(id) ON DELETE CASCADE;

DROP TABLE IF EXISTS techmaps_platform.stage CASCADE;

CREATE TABLE techmaps_platform.stage (
    id uuid NOT NULL,
    roadmap_id uuid NOT NULL,
    name VARCHAR(255) NOT NULL
);

ALTER TABLE techmaps_platform.stage OWNER TO "techmaps";

ALTER TABLE techmaps_platform.stage
    ADD CONSTRAINT stage_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.stage
    ADD CONSTRAINT stage_roadmap_user_id_fkey FOREIGN KEY (roadmap_id)
        REFERENCES techmaps_platform.roadmap(id) ON DELETE CASCADE;

DROP TABLE IF EXISTS techmaps_platform.stage_user CASCADE;

CREATE TABLE techmaps_platform.stage_user (
    id uuid NOT NULL,
    stage_id uuid NOT NULL,
    roadmap_user_id uuid NOT NULL,
    is_done BOOLEAN NOT NULL,
    position INTEGER NOT NULL
);

ALTER TABLE techmaps_platform.stage_user OWNER TO "techmaps";

ALTER TABLE techmaps_platform.stage_user
    ADD CONSTRAINT stage_user_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.stage_user
    ADD CONSTRAINT stage_user_stage_id_fkey FOREIGN KEY (stage_id)
        REFERENCES techmaps_platform.stage(id) ON DELETE CASCADE;

ALTER TABLE techmaps_platform.stage_user
    ADD CONSTRAINT stage_user_roadmap_user_id_fkey FOREIGN KEY (roadmap_user_id)
        REFERENCES techmaps_platform.roadmap_user(id) ON DELETE CASCADE;

DROP TABLE IF EXISTS techmaps_platform.task CASCADE;

CREATE TABLE techmaps_platform.task(
    id uuid NOT NULL,
    stage_id uuid NOT NULL,
    title varchar(255) NOT NULL,
    description varchar NOT NULL,
    position int NOT NULL
);

ALTER TABLE techmaps_platform.task OWNER TO "techmaps";

ALTER TABLE techmaps_platform.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.task
    ADD CONSTRAINT task_stage_id_fkey FOREIGN KEY (stage_id)
        REFERENCES techmaps_platform.stage(id) ON DELETE CASCADE;

CREATE TABLE techmaps_platform.task_user (
    id uuid NOT NULL,
    task_id uuid NOT NULL,
    roadmap_user_id uuid NOT NULL,
    is_done BOOLEAN NOT NULL
);

ALTER TABLE techmaps_platform.task_user OWNER TO "techmaps";

ALTER TABLE techmaps_platform.task_user
    ADD CONSTRAINT task_user_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.task_user
    ADD CONSTRAINT task_user_task_id_fkey FOREIGN KEY (task_id)
        REFERENCES techmaps_platform.task(id) ON DELETE CASCADE;

ALTER TABLE techmaps_platform.task_user
    ADD CONSTRAINT task_user_roadmap_user_id_fkey FOREIGN KEY (roadmap_user_id)
        REFERENCES techmaps_platform.roadmap_user(id) ON DELETE CASCADE;

DROP TABLE IF EXISTS techmaps_platform.step CASCADE;

CREATE TABLE techmaps_platform.step (
    id uuid NOT NULL,
    task_id uuid NOT NULL,
    position integer NOT NULL,
    text varchar(255) NOT NULL,
    link varchar(255) NOT NULL
);

ALTER TABLE techmaps_platform.step OWNER TO "techmaps";

ALTER TABLE techmaps_platform.step
    ADD CONSTRAINT step_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.step
    ADD CONSTRAINT step_task_id_fkey FOREIGN KEY (task_id)
        REFERENCES techmaps_platform.task(id) ON DELETE CASCADE;

DROP TABLE IF EXISTS techmaps_platform.step_user CASCADE;

CREATE TABLE techmaps_platform.step_user (
    id uuid NOT NULL,
    step_id uuid NOT NULL,
    roadmap_user_id uuid NOT NULL,
    is_done BOOLEAN NOT NULL
);

ALTER TABLE techmaps_platform.step_user OWNER TO "techmaps";

ALTER TABLE techmaps_platform.step_user
    ADD CONSTRAINT step_user_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.step_user
    ADD CONSTRAINT step_user_roadmap_user_id_fkey FOREIGN KEY (roadmap_user_id)
        REFERENCES techmaps_platform.roadmap_user(id) ON DELETE CASCADE;