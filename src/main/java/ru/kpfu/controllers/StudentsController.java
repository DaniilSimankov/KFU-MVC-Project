package ru.kpfu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.services.StudentsService;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentsService studentsService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllStudents(Model model){

        model.addAttribute("students", studentsService.getAllStudents());

        return "students/students";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getStudentPage(Model model, @PathVariable Long id){

        model.addAttribute("student", studentsService.getStudentById(id));
        model.addAttribute("state", studentsService.getStateByStudentId(id).name());

        return "students/info";
    }

    @PostMapping("{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProfileById(@PathVariable Long id){

        studentsService.deleteStudentById(id);

        return "redirect:/students";
    }

    @PostMapping("{id}/ban")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String banProfileById(@PathVariable Long id){

        studentsService.banStudentById(id);

        return "redirect:/students";
    }

    @PostMapping("{id}/reinstate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String reinstateProfileById(@PathVariable Long id){

        studentsService.reinstateStudentById(id);

        return "redirect:/students";
    }



//    @PreAuthorize("hasAuthority('ADMIN')")



}
