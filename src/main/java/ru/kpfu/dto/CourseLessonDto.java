package ru.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.models.Course;
import ru.kpfu.models.Lesson;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseLessonDto {
    private Long id;
    private String title;
    private List<LessonDto> lessons;
    boolean isRunning;

    public static CourseLessonDto from(Course course){
        return CourseLessonDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .lessons(course.getLessons().stream().map(LessonDto::from).collect(Collectors.toList()))
                .build();
    }

    public static List<CourseLessonDto> from(Set<Course> courses){
        return courses.stream().map(CourseLessonDto::from).collect(Collectors.toList());
    }
}
