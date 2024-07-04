package com.example.rubrica.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UtentiDto {

    private Integer id;

    @NotBlank(message = "Il nome non può essere vuoto o una stringa vuota")
    private String name;

    @NotNull(message = "Il campo età non può essere nullo")
    @Min(value = 5, message = "L'' utente deve avere almeno 5 anni")
    @Max(value = 99, message = "L'' utente deve avere al massimo 99 anni")
    private Integer age;

    private List<String> phoneNumbers = new ArrayList<>();
}
