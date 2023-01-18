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
        return facultyRepository.findById(id).get();
    }
    public Faculty editFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {

        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculty() {

        return facultyRepository.findAll();
    }

    public List<Faculty> getColor (String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(e -> e.getColor().equals(color))
                .toList();
    }
}
