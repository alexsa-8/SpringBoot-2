-- liquibase formatted sql

-- changeset Alex:1
CREATE INDEX student_name_index ON students (name);