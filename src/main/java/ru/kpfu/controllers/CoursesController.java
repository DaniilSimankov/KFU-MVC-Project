package ru.kpfu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.dto.CourseDto;
import ru.kpfu.dto.CourseLessonDto;
import ru.kpfu.dto.LessonDto;
import ru.kpfu.services.CourseStudentLessonService;
import ru.kpfu.services.CoursesLessonsService;
import ru.kpfu.services.CoursesService;
import ru.kpfu.services.StudentCourseService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CoursesController {

    private final CoursesService coursesService;
    private final StudentCourseService studentCourseService;
    private final CoursesLessonsService coursesLessonsService;
    private final CourseStudentLessonService courseStudentLessonService;

    @GetMapping
    public String getAllCoursesPage(Model model, Authentication authentication) {

        model.addAttribute("courses", coursesService.getAllCourses());
        model.addAttribute("admin", authentication != null && authentication.getAuthorities().toString().contains("ADMIN"));

        return "courses/courses";
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public String getCoursePage(Model model, @PathVariable Long id, Authentication authentication) {

        model.addAttribute("course", coursesService.getCourseWithLessonsById(id));
        model.addAttribute("isRunning", studentCourseService.isRunningCourse(id, authentication.getName()));
        model.addAttribute("admin", authentication.getAuthorities().toString().contains("ADMIN"));

        return "courses/course";
    }

    @PostMapping("/{id}/subscribe")
    @PreAuthorize("isAuthenticated()")
    public String addCourse(@PathVariable Long id, Authentication authentication) {

        studentCourseService.addStudentToCourseByEmail(id, authentication.getName());

        return "redirect:/courses";
    }

    @PostMapping("/{id}/unsubscribe")
    @PreAuthorize("isAuthenticated()")
    public String deleteStudentFromCourse(@PathVariable Long id, Authentication authentication) {

        studentCourseService.deleteStudentFromCourseByStudentEmail(id, authentication.getName());

        return "redirect:/courses";
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCourse(@PathVariable Long id, Authentication authentication) {

        courseStudentLessonService.deleteCourse(id);

        return "redirect:/courses";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editCourse(Model model, @PathVariable Long id) {

        model.addAttribute("course", coursesService.getCourseWithLessonsById(id));
        model.addAttribute("newLesson", new LessonDto());

        return "courses/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateCourse(@PathVariable Long id, @ModelAttribute("course") @Valid CourseLessonDto course, @ModelAttribute("newLesson") @Valid LessonDto newLesson,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "courses/edit";

        coursesLessonsService.updateCourse(id, course);

        return "redirect:/courses/" + id;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createNewCourse(Model model) {

        model.addAttribute("newCourse", new CourseDto());

        return "courses/create";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addNewCourse(@ModelAttribute("newCourse") @Valid CourseDto newCourse,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "courses/create";

        coursesService.addCourse(newCourse);

        return "redirect:/courses";
    }

    @GetMapping("/{id}/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getStudentsInCourse(Model model, @PathVariable Long id) {

        model.addAttribute("course", coursesService.getCourseById(id));
        model.addAttribute("students", courseStudentLessonService.findStudentsByCourseId(id));

        return "courses/students";
    }

    @PostMapping("/{id}/students/delete/{id_student}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteStudentFromCourse(@PathVariable Long id, @PathVariable Long id_student) {

        studentCourseService.deleteStudentFromCourseByStudentId(id, id_student);

        return "redirect:/courses/" + id + "/students";
    }

}
