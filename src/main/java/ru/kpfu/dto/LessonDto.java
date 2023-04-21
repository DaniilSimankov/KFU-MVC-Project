package ru.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.models.Lesson;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonDto {
    private Long id;
    @NotBlank
    private String name;

    public static LessonDto from(Lesson lesson){
        return LessonDto.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .build();
    }

    public static List<LessonDto> from(List<Lesson> lessons){
        return lessons.stream().map(LessonDto::from).collect(Collectors.toList());
    }
}
