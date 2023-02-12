package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        logger.info("Request to create a student {}", student);
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("Request to getting student by id {}", id);
        return studentRepository.findById(id).orElse(null);//get();
    }

    public Student editStudent(Student student) {
        logger.info("Request to edit student {}", student);
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        logger.error("Request to student not find");
        return null;
    }

    public void deleteStudent(Long id) {
        logger.info("Request to delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        logger.info("Request to getting all students");
        return studentRepository.findAll();
    }

    public List<Student> findByAgeEquals(int age) {
        logger.info("Request to getting student by age {}", age);
        return studentRepository.findAll()
                .stream()
                .filter(e -> e.getAge() >= age)
                .toList();
    }

    public Student findByName(String name) {
        logger.info("Request to getting student by name {}", name);
        return studentRepository.findByNameIgnoreCase(name);
    }

    public Collection<Student> getAllStudentByAgeBetween(int ageMin, int ageMax) {
        logger.debug("Request to getting students aged from {}, to {}", ageMin, ageMax);
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public Collection<Student> findById(Long studentId) {
        logger.info("Request to getting faculty of student id {}", studentId);
        return studentRepository.findStudentsByFacultyId(studentId);
    }

    public Integer getAllStudents() {
        logger.info("Request to getting number of students");
        return studentRepository.getAllStudents();
    }

    public Double getAverageAge() {
        logger.info("Request to getting average age of students");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastStudents() {
        logger.info("Request to getting last 5 students");
        return studentRepository.getLastStudent();
    }
}
