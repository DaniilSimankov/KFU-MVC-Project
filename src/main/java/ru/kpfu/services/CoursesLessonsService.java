package ru.kpfu.services;

import ru.kpfu.dto.CourseLessonDto;
import ru.kpfu.dto.LessonDto;

public interface CoursesLessonsService {
    void deleteFromCourseLessonById(Long idCourse, Long idLesson);

    void updateCourse(Long id, CourseLessonDto course);

    void addNewLesson(Long id, LessonDto newLesson);
}
