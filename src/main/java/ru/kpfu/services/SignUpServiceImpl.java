package ru.kpfu.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.dto.SignUpForm;
import ru.kpfu.models.Student;
import ru.kpfu.repositories.StudentsRepository;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final PasswordEncoder passwordEncoder;
    private final StudentsRepository studentsRepository;

    @Override
    public void signUp(SignUpForm form) {
        Student student = Student.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .password(passwordEncoder.encode(form.getPassword()))
                .role(Student.Role.USER)
                .state(Student.State.CONFIRMED)
                .build();

        studentsRepository.save(student);
    }

}
