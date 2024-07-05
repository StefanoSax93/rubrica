package com.example.rubrica.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull(message = "Il campo età non può essere nullo")
    @Min(value = 5, message = "L'' utente deve avere almeno 5 anni")
    @Max(value = 99, message = "L'' utente deve avere al massimo 99 anni")
    private Integer age;
    @NotEmpty
    @Email
    private String email;
    @Size(min=6, message="la password deve contenere almeno 6 caratteri")
    private String password;
    private String confirmPassword;
}
