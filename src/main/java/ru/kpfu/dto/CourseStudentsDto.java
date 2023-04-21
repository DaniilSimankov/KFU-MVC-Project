package ru.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.models.Course;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseStudentsDto {
    private Long id;
    private String title;
    private List<StudentTitleDto> students;

    public static CourseStudentsDto from(Course course){
        return CourseStudentsDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .students(course.getStudents().stream().map(StudentTitleDto::from).collect(Collectors.toList()))
                .build();
    }

    public static List<CourseStudentsDto> from(Set<Course> courses){
        return courses.stream().map(CourseStudentsDto::from).collect(Collectors.toList());
    }
}
