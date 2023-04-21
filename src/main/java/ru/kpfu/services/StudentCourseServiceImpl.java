package ru.kpfu.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.models.Course;
import ru.kpfu.models.Student;
import ru.kpfu.repositories.CoursesRepository;
import ru.kpfu.repositories.StudentsRepository;

@Service
@RequiredArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {

    private final CoursesRepository coursesRepository;
    private final StudentsRepository studentsRepository;

    @Override
    public void addStudentToCourseByEmail(Long id, String email) {
        Student student = studentsRepository.getByEmail(email);
        Course existedCourse = coursesRepository.getById(id);
        student.getCourses().add(existedCourse);
        studentsRepository.save(student);
    }

    @Override
    public void deleteStudentFromCourseByStudentEmail(Long id, String email) {
        Student student = studentsRepository.getByEmail(email);
        Course existedCourse = coursesRepository.getById(id);
        student.getCourses().remove(existedCourse);
        studentsRepository.save(student);
    }

    public Boolean isRunningCourse(Long id, String email){
        Student student = studentsRepository.getByEmail(email);
        Course existedCourse = coursesRepository.getById(id);

        return student.getCourses().contains(existedCourse);
    }

    @Override
    public void deleteStudentFromCourseByStudentId(Long id_course, Long id_student) {
        Student student = studentsRepository.getById(id_student);
        Course existedCourse = coursesRepository.getById(id_course);
        student.getCourses().remove(existedCourse);
        studentsRepository.save(student);
    }

}
