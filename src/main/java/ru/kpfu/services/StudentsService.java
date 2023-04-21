package ru.kpfu.services;

import ru.kpfu.dto.StudentDto;
import ru.kpfu.models.Student;

import java.util.List;

public interface StudentsService {
    List<Student> getAllStudents();

    StudentDto getStudentByEmail(String email);

    void updateStudent(String email, StudentDto updatedStudent);

    StudentDto getStudentById(Long id);

    void deleteStudentById(Long id);

    Student.State getStateByStudentId(Long id);

    void banStudentById(Long id);

    void reinstateStudentById(Long id);

    List<StudentDto> findStudentByEmailContaining(String email); //for search
}
