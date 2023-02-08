package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
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

    @GetMapping
    public ResponseEntity<Collection<Student>> getAll() {

        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping  //POST http://localhost:8080/students
    public Student createStudent(@RequestBody Student student) {

        return studentService.createStudent(student);
    }

    @PutMapping   //PUT http://localhost:8080/students
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
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

    @GetMapping("/faculty/{studentId}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.findStudent(studentId).getFaculty());
    }

    @GetMapping("/all")
    public ResponseEntity<Integer> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/last-students")
    public ResponseEntity<List<Student>> getLastStudents() {
        return ResponseEntity.ok(studentService.getLastStudents());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable("name") String name){
        List<Student> students = Collections.singletonList(studentService.findByName(name));
        return ResponseEntity.ok(students);
    }
}
