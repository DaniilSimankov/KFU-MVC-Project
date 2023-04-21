package ru.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.models.Course;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private Long id;
    @NotBlank
    private String title;

    public static CourseDto from(Course course){
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .build();
    }

    public static List<CourseDto> from(Set<Course> courses){
        return courses.stream().map(CourseDto::from).collect(Collectors.toList());
    }
}
