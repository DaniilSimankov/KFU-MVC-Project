package ru.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.models.Course;
import ru.kpfu.models.Student;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    @Size(min = 4, max = 20, message = "Имя должно содержать от 4 до 30 букв")
    private String firstName;
    @Size(min = 4, max = 20, message = "Фамилия должна содержать от 4 до 30 букв")
    private String lastName;
    private String email;
    private List<CourseDto> courses;

    public static StudentDto from(Student student){
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .courses(student.getCourses().stream().map(CourseDto::from).collect(Collectors.toList()))
                .build();
    }

    public static List<StudentDto> from(List<Student> accounts){
        return accounts.stream().map(StudentDto::from).collect(Collectors.toList());

    }

}
