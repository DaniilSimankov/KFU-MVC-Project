package ru.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.models.Student;

import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentUpdateForm {
    private String firstName;
    private String lastName;

    public static StudentUpdateForm from(Student student){
        return StudentUpdateForm.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();
    }
}
