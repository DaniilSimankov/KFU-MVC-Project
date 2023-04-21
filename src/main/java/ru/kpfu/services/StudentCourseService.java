package ru.kpfu.services;

public interface StudentCourseService {
    void addStudentToCourseByEmail(Long id, String email);

    void deleteStudentFromCourseByStudentEmail(Long id, String email);

    Boolean isRunningCourse(Long id, String email);

    void deleteStudentFromCourseByStudentId(Long id_course, Long id_student);
}
