package ru.hogwarts.school.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Long> {
//    List<Student> getStudentsByName(String name);
    List<Student> findByAge(int age);
    Student findByNameIgnoreCase(String name);
    Collection<Student> findByAgeBetween(int ageMin, int ageMax); //ContainsIgnoreCase-присоединяется к названию метода если переменная String
    Collection<Student> findStudentsByFacultyId(Long id);
    @Query(value = "SELECT COUNT(*) as count FROM student", nativeQuery = true)
    Integer getAllStudents();

    @Query(value = "SELECT AVG(age) as count FROM student", nativeQuery = true)
    Double getAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY  id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastStudent();
    //Collection<Student> findStudentByNameIgnoreCaseAndAge(String name, int age);//поиск студента по имени (с большой или маленькой буквы) или сколько лет
    //Collection<Student> findStudentByNameOrAge(String name, int age);//поиск по имени или годам
}
