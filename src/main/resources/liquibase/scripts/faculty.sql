-- liquibase formatted sql

-- chengeset Alex:1
CREATE INDEX faculty_name_and_color_index ON faculty (name, color);