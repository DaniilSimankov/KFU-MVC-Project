package ru.kpfu.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.dto.CourseDto;
import ru.kpfu.dto.CourseLessonDto;
import ru.kpfu.dto.LessonDto;
import ru.kpfu.models.Course;
import ru.kpfu.repositories.CoursesRepository;
import ru.kpfu.repositories.StudentsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;

    @Override
    public List<Course> getAllCoursesByStudentId(Long id) {
        return coursesRepository.findAllByStudentsId(id);
    }

    @Override
    public CourseDto getCourseById(Long id) {
        return CourseDto.from(coursesRepository.getById(id));
    }

    @Override
    public CourseLessonDto getCourseWithLessonsById(Long id) {
        return CourseLessonDto.from(coursesRepository.getById(id));
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return CourseDto.from(new HashSet<>(coursesRepository.findAll()));
    }



    @Override
    public void addCourse(CourseDto newCourse) {
        Course course = Course
                .builder()
                .title(newCourse.getTitle())
                .build();
        coursesRepository.save(course);
    }


}
