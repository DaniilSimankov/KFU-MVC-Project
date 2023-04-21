package ru.kpfu.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.dto.StudentTitleDto;
import ru.kpfu.models.Course;
import ru.kpfu.models.Student;
import ru.kpfu.repositories.CoursesRepository;
import ru.kpfu.repositories.LessonsRepository;
import ru.kpfu.repositories.StudentsRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseStudentLessonServiceImpl implements CourseStudentLessonService {
    private final LessonsRepository lessonsRepository;
    private final CoursesRepository coursesRepository;
    private final StudentsRepository studentsRepository;

    @Override
    public List<StudentTitleDto> findStudentsByCourseId(Long id) {
        Course course = coursesRepository.getById(id);

        List<Student> students = studentsRepository.findStudentsByCoursesContaining(course);

        return students.stream().map(StudentTitleDto::from).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        lessonsRepository.deleteAllByCourse_Id(id);

        Course course = coursesRepository.getById(id);

        List<Student> students = studentsRepository.findStudentsByCoursesContaining(course);

        for(Student student : students){
            student.getCourses().remove(course);
        }

        coursesRepository.deleteById(id);
    }



}
