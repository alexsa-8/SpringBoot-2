package ru.hogwarts.school.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Long> {
    List<Student> findByAgeEquals(int age);
    Student findByName(String name);
    Collection<Student> findStudentsByAge(int age);
    Collection<Student> findAllByNameContains(String part);
}
