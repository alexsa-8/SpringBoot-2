-- liquibase formatted sql

-- changeset Alex:1
CREATE INDEX IF NOT EXISTS student_name_index ON students (name);