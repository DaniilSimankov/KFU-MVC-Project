package ru.kpfu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.models.Course;
import ru.kpfu.models.Student;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentsRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    List<Student> findAllByState(Student.State state);

    List<Student> findStudentsByEmailContainingIgnoreCase(String email); // for search

    Student getByEmail(String email);

    List<Student> findStudentsByCoursesContaining(Course courses);

}
