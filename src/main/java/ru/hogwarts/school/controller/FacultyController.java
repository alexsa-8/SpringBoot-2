package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {

        this.facultyService = facultyService;
    }

    @GetMapping("{id}")  //GET http://localhost:8080/faculties/23 (id=23)
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping  //GET http://localhost:8080/faculties
    public ResponseEntity findFaculties(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String color,
                                        @RequestParam(required = false) Long id) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByName(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColorEquals(color));
        }
        if (id != null) {
            return ResponseEntity.ok(facultyService.getFacultyByStudentId(id));
        }
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @PostMapping  //POST http://localhost:8080/faculties
    public Faculty createFaculty(@RequestBody Faculty faculty) {

        return facultyService.createFaculty(faculty);
    }

    @PutMapping  //PUT http://localhost:8080/faculties
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}") //DELETE http://localhost:8080/faculties/23 (id=23)
    public ResponseEntity<Void> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter/{color}")  //GET http://localhost:8080/faculties/filter/15 (age=15)
    public ResponseEntity<List<Faculty>> getColor(@PathVariable String color) {

        return ResponseEntity.ok(facultyService.findByColorEquals(color));
    }

    @GetMapping("colorOrName")
    public ResponseEntity<Collection<Faculty>> getByNameOrColor(@RequestParam(required = false) String name,
                                                                @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.getFacultyByNameOrColor(name, color));
    }

    //    @GetMapping("students/{id}")
//    public ResponseEntity<Collection<Faculty>> getFacultyByStudentId(@PathVariable Long id) {
//        return ResponseEntity.ok(facultyService.getFacultyByStudentId(id));
//    }
    @GetMapping("students/{id}")
    public ResponseEntity<Faculty> getFacultyId(@PathVariable long id) {
        Faculty faculty = (Faculty) facultyService.getFacultyByStudentId(id);
        return ResponseEntity.ok(faculty);
    }
}
