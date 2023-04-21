package ru.kpfu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpForm {
    @Size(min = 4, max = 20, message = "Имя должно содержать от 4 до 30 букв")
//    @NotEmpty(message = "Имя не должно быть пустым")
    @NotBlank(message = "Имя не должно быть пустым")
    private String firstName;

    @Size(min = 4, max = 20, message = "Фамилия должно содержать от 4 до 30 букв")
//    @NotEmpty(message = "Surname should not be empty")
    @NotBlank(message = "Фамилия не должна быть пустой")
    private String lastName;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Почта не подходит по формату")
    @NotBlank(message = "Почта не должна быть пустой")
//    @NotEmpty(message = "Password should not be empty")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
//    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=.*\\D).{8,}$",
            message = "Слишком простой пароль. Пароль должен содержать A-Z, a-z, 0-9 и символы")
    private String password;

}

//        String email_pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//        String pattern_password = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=.*\\D).{8,}$";