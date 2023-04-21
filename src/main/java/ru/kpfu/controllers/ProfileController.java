package ru.kpfu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.dto.StudentDto;
import ru.kpfu.services.StudentsService;

import javax.validation.Valid;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ProfileController {

    private final StudentsService studentsService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication){
//        model.addAttribute("profile", studentsService.getStudentByEmail(((UserDetails)authentication.getPrincipal()).getUsername()));
        model.addAttribute("profile", studentsService.getStudentByEmail(authentication.getName()));

        return "profile/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/edit")
    public String editProfile(Model model, Authentication authentication){
        model.addAttribute("profile", studentsService.getStudentByEmail(authentication.getName()));

        return "profile/edit";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/edit")
    public String updateProfile(Authentication authentication, @ModelAttribute("profile") @Valid StudentDto student,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "profile/edit";

        studentsService.updateStudent(authentication.getName(), student);

        return "redirect:/profile";
    }


    @PostMapping("/profile/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deleteProfileById(@PathVariable Long id){

        studentsService.deleteStudentById(id);

        return "redirect:/logout";
    }
}
