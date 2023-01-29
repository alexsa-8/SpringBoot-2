package ru.hogwarts.school.service;

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

    public Student createStudent(Student student) {

        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {

        return studentRepository.findById(id).orElse(null);//get();
    }

    public Student editStudent(Student student) {
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findByAgeEquals(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(e -> e.getAge() >= age)
                .toList();
    }

    public Student findByName(String name) {
        return studentRepository.findByNameIgnoreCase(name);
    }

    public Collection<Student> getAllStudentByAgeBetween(int ageMin, int ageMax) {
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public Student findById(long studentId) {

        return studentRepository.findStudentsByFacultyId(studentId);
    }
}
