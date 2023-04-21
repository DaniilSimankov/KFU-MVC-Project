package ru.kpfu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.dto.CourseLessonDto;
import ru.kpfu.dto.LessonDto;
import ru.kpfu.services.CoursesLessonsService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class LessonsController {

    private final CoursesLessonsService coursesLessonsService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{idCourse}/lessons/delete/{idLesson}")
    public String deleteLesson(@PathVariable Long idCourse, @PathVariable Long idLesson) {

        coursesLessonsService.deleteFromCourseLessonById(idCourse, idLesson);

        return "redirect:/courses/" + idCourse + "/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/lessons")
    public String addLessonToCourse(@PathVariable Long id, @ModelAttribute("newLesson") @Valid LessonDto newLesson,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "redirect:/courses/" + id + "/edit";

        if (newLesson.getName() != null)
            coursesLessonsService.addNewLesson(id, newLesson);

        return "redirect:/courses/" + id + "/edit";
    }


}
