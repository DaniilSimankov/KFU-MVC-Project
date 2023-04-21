package ru.kpfu.services;

import ru.kpfu.dto.StudentTitleDto;
import ru.kpfu.models.Student;

import java.util.List;

public interface CourseStudentLessonService {

    List<StudentTitleDto> findStudentsByCourseId(Long id);

    void deleteCourse(Long id);

}
