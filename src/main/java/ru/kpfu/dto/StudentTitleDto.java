package ru.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.models.Course;
import ru.kpfu.models.Student;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentTitleDto {
    private Long id;
    private String email;

    public static StudentTitleDto from(Student student){
        return StudentTitleDto.builder()
                .id(student.getId())
                .email(student.getEmail())
                .build();
    }

    public static List<StudentTitleDto> from(List<Student> accounts){
        return accounts.stream().map(StudentTitleDto::from).collect(Collectors.toList());

    }

}
