package ru.kpfu.repositories;

import ru.kpfu.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByStudentsId(Long students_id);

    List<Course> findAll();

    Course getById(Long id);

}
