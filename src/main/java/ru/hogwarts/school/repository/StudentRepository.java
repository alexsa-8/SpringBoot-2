package ru.hogwarts.school.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Long> {
    List<Student> findByAgeEquals(int age);
    Student findByNameIgnoreCase(String name);
    Collection<Student> findByAgeBetween(int ageMin, int ageMax); //ContainsIgnoreCase-присоединяется к названию метода если переменная String
    Collection<Student> findStudentsByFacultyId(Long id);
    //Collection<Student> findStudentByNameIgnoreCaseAndAge(String name, int age);//поиск студента по имени (с большой или маленькой буквы) или сколько лет
    //Collection<Student> findStudentByNameOrAge(String name, int age);//поиск по имени или годам
}
