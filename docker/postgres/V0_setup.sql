CREATE ROLE "techmaps" WITH SUPERUSER;
CREATE USER "techmaps-ro" WITH PASSWORD 'techmaps-ro' IN ROLE "techmaps";
CREATE USER "techmaps-dml" WITH PASSWORD 'techmaps-dml' IN ROLE "techmaps";
CREATE USER "techmaps-app" WITH PASSWORD 'techmaps-app' IN ROLE "techmaps";

ALTER USER "techmaps-app" SET search_path = public, techmaps;

CREATE DATABASE "techmaps" WITH OWNER "techmaps-app";