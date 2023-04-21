package ru.kpfu.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.repositories.StudentsRepository;

@Service
@RequiredArgsConstructor
public class StudentUserDetailsService  implements UserDetailsService {

    private final StudentsRepository studentsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new StudentUserDetails(studentsRepository
                .findByEmail(email).orElseThrow(
                        ()->new UsernameNotFoundException("Student not found")));
    }
}
