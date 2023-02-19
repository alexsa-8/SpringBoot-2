package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public final Object flag = new Object();
    int count = 0;
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
        return studentRepository.findAll()
                .stream()
                .collect(Collectors.averagingDouble(Student::getAge));
    }

    public List<Student> getLastStudents() {
        logger.info("Request to getting last 5 students");
        return studentRepository.getLastStudent();
    }

    public List<String> getStudentsByNameOnD() {
        logger.info("List of student names beginning with D");
        return studentRepository.findAll()
                .stream()
                .filter(name -> (name.getName().startsWith("D")))
                .map(name -> name.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public Integer lessTime() {
        long start = System.currentTimeMillis();
//        int sum = Stream.iterate(1, a -> a + 1)
//                .limit(1_000_000)
//                .mapToInt(Integer::intValue)
//                .sum();
        int starting = 1_000_000;
        int finish = IntStream.range(1, starting+1).sum();
        long lessTime = System.currentTimeMillis() - start;
        logger.info("Request for time: " + lessTime + "ms");
        return finish;
    }
    public void streamWithStudentNames() {
        logger.info("Request to the receiving stream");
        List<String> studentList = getList();

        System.out.println(Thread.currentThread().getName() + " " + studentList.get(0));
        System.out.println(Thread.currentThread().getName() + " " + studentList.get(1));

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + studentList.get(2));
            System.out.println(Thread.currentThread().getName() + " " + studentList.get(3));
        });

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + studentList.get(4));
            System.out.println(Thread.currentThread().getName() + " " + studentList.get(5));
        });
        thread.start();
        thread1.start();
    }


    public void streamOfStudentNames() {
        logger.info("Synchronized stream of student names");
        List<String> studentList = getList();

        printStudentName(studentList.get(0));
        printStudentName(studentList.get(1));

        Thread thread = new Thread(() -> {
            printStudentName(studentList.get(2));
            printStudentName(studentList.get(3));
        });

        Thread thread1 = new Thread(() -> {
            printStudentName(studentList.get(4));
            printStudentName(studentList.get(5));
        });

        thread.start();
        thread1.start();
    }

    public void printStudentName(String student) {
        synchronized (flag) {
            System.out.println(Thread.currentThread().getName() + ": " + student + " - count: " + count);
            count++;
        }
    }

    private List<String> getList() {
        return studentRepository
                .findAll()
                .stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }
}
