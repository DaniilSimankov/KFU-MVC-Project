package ru.kpfu.services;

import ru.kpfu.dto.CourseDto;
import ru.kpfu.dto.CourseLessonDto;
import ru.kpfu.models.Course;

import java.util.List;

public interface CoursesService {
    List<Course> getAllCoursesByStudentId(Long id);

    CourseDto getCourseById(Long id);

    CourseLessonDto getCourseWithLessonsById(Long id);

    List<CourseDto> getAllCourses();

    void addCourse(CourseDto newCourse);
}
