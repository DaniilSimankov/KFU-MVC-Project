package ru.kpfu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.models.Lesson;

import java.util.List;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> getAllByCourseId(Long course_id);

    void deleteAllByCourse_Id(Long id);
}
