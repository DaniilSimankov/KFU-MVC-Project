package ru.kpfu.controllers;

import lombok.RequiredArgsConstructor;
import org.hibernate.NonUniqueObjectException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.dto.SignUpForm;
import ru.kpfu.dto.StudentDto;
import ru.kpfu.models.Student;
import ru.kpfu.services.SignUpService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(@ModelAttribute("signUpForm") SignUpForm form, Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }

//        model.addAttribute("signUpForm", new SignUpForm());
        return "signUp";
    }

    @PostMapping
    public String signUp(@ModelAttribute("signUpForm") @Valid SignUpForm form, BindingResult result) {

        if (result.hasErrors()) {
//            model.addAttribute("signUpForm", student);
            return "signUp";
        }
        try {
            signUpService.signUp(form);
        }catch (Exception e){
            FieldError error = new FieldError("signUpForm", "email",
                    "Пользователь с такой почтой уже существует");
            result.addError(error);
            return "signUp";
        }
        return "redirect:/signIn";
    }

}


