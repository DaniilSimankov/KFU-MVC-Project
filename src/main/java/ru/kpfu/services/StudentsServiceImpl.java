package ru.kpfu.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.dto.StudentDto;
import ru.kpfu.models.Student;
import ru.kpfu.repositories.StudentsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;

    @Override
    public List<Student> getAllStudents() {
        return  studentsRepository.findAll();
//        return studentsRepository.findAllByState(Student.State.CONFIRMED);
    }

    public StudentDto getStudentByEmail(String email){
        return StudentDto.from(studentsRepository.getByEmail(email));
    }

    @Override
    public void updateStudent(String email, StudentDto updatedStudent) {
        Student student = studentsRepository.getByEmail(email);
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());

        studentsRepository.save(student);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return StudentDto.from(studentsRepository.getById(id));
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = studentsRepository.getById(id);
        student.getCourses().clear();
        student.setState(Student.State.DELETED);

        studentsRepository.save(student);
    }

    @Override
    public Student.State getStateByStudentId(Long id) {
        return studentsRepository.getById(id).getState();
    }

    @Override
    public void banStudentById(Long id) {
        Student student = studentsRepository.getById(id);
        // надо ли?
//        student.getCourses().clear();
        student.setState(Student.State.BANNED);

        studentsRepository.save(student);
    }

    @Override
    public void reinstateStudentById(Long id) {
        Student student = studentsRepository.getById(id);
        student.setState(Student.State.CONFIRMED);

        studentsRepository.save(student);
    }

    @Override
    public List<StudentDto> findStudentByEmailContaining(String email) {
        return StudentDto.from(studentsRepository.findStudentsByEmailContainingIgnoreCase(email));
    }
}
