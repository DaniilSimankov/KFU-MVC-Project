package ru.kpfu.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.dto.CourseLessonDto;
import ru.kpfu.dto.LessonDto;
import ru.kpfu.models.Course;
import ru.kpfu.models.Lesson;
import ru.kpfu.repositories.CoursesRepository;
import ru.kpfu.repositories.LessonsRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CoursesLessonsServiceImpl implements CoursesLessonsService {

    private final LessonsRepository lessonsRepository;
    private final CoursesRepository coursesRepository;

    @Override
    public void deleteFromCourseLessonById(Long idCourse, Long idLesson) {
        Course course = coursesRepository.getById(idCourse);
        Lesson lesson = lessonsRepository.getById(idLesson);
        course.getLessons().remove(lesson);
        lessonsRepository.delete(lesson);

        coursesRepository.save(course);
    }

    @Override
    public void updateCourse(Long id, CourseLessonDto updatedCourse) {
        Course existedCourse = coursesRepository.getById(id);
        existedCourse.setTitle(updatedCourse.getTitle());

        for (Lesson lesson : existedCourse.getLessons()) {
            for (LessonDto updatedLesson : updatedCourse.getLessons()) {
                if (lesson.getId().equals(updatedLesson.getId()) && !lesson.getName().equals(updatedLesson.getName())) {
                    lesson.setName(updatedLesson.getName());
                    lessonsRepository.save(lesson);
                }
            }
        }

//        existedCourse.getLessons().stream().map(lesson -> {
//            LessonDto updatedLesson = updatedCourse.getLessons().stream()
//                    .filter(l -> l.getId().equals(lesson.getId()))
//                    .findAny()
//                    .orElse(null);
//            if (!Objects.equals(lesson.getName(), updatedLesson.getName())) {
//                lesson.setName(updatedLesson.getName());
//                lessonsRepository.save(lesson);
//            }
//            return lesson;
//        });

        coursesRepository.save(existedCourse);
    }

    @Override
    public void addNewLesson(Long id, LessonDto newLesson) {

        Course course = coursesRepository.getById(id);
        Lesson lesson = Lesson.builder()
                .name(newLesson.getName())
                .course(course)
                .build();

        lessonsRepository.save(lesson);
    }


}
