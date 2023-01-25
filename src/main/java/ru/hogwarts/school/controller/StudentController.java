package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping("{id}") //GET http://localhost:8080/students/23 (id=23)
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping  //GET http://localhost:8080/students
    public ResponseEntity findStudents(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) int age,
                                       @RequestParam(required = false) Long id) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(studentService.findByName(name));
        }
        if (age != 0) {
            return ResponseEntity.ok(studentService.findByAgeEquals(age));
        }
        if (id != null) {
            return ResponseEntity.ok(studentService.getFaculty(id));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping  //POST http://localhost:8080/students
    public Student createStudent(@RequestBody Student student) {

        return studentService.createStudent(student);
    }

    @PutMapping   //PUT http://localhost:8080/students
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")  //DELETE http://localhost:8080/students/23 (id=23)
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter/{age}")  //GET http://localhost:8080/students/filter/15 (age=15)
    public ResponseEntity<List<Student>> getAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.findByAgeEquals(age));
    }
    @GetMapping("/ageBetween")
    public ResponseEntity<Collection<Student>> getStudentByAgeBetween(@RequestParam int ageMin,
                                                                      @RequestParam int ageMax) {
        return ResponseEntity.ok(studentService.getAllStudentByAgeBetween(ageMin, ageMax));
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<Collection<Student>> getFaculty(@PathVariable long id) {
        Collection<Student> students = studentService.getFaculty(id);
        return ResponseEntity.ok(studentService.getFaculty(id));
    }
}
