-- liquibase formatted sql

-- changeset Alex:2
CREATE INDEX IF NOT EXISTS faculty_name_and_color_index ON faculty (name, color);