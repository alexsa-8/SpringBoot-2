package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository <Faculty, Long> {
    List<Faculty> findByColorEquals(String color);

    Faculty findByNameIgnoreCase(String name);
    Collection<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
    Collection<Faculty> findFacultyByStudentId(Long id);
}
