package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {

        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {

        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {

        if (facultyRepository.existsById(faculty.getId())) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculty() {

        return facultyRepository.findAll();
    }

    public List<Faculty> findByColorEquals(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(e -> e.getColor().equals(color))
                .toList();
    }

    public Faculty findByName(String name) {

        return facultyRepository.findByNameIgnoreCase(name);
    }

    public Collection<Faculty> getFacultyByNameOrColor(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Collection<Faculty> getFacultyByStudentId(long id) {

        return facultyRepository.findFacultyByStudentId(id);
    }
}
