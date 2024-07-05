package com.example.rubrica.dto;

import com.example.rubrica.model.Phone;
import jakarta.validation.Valid;
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

    @NotBlank(message = "L'' email non può essere vuota o una stringa vuota")
    @Email(message = "L'' email deve contenere la chiocciola @")
    private String email;


    @NotNull(message = "Il campo età non può essere nullo")
    @Min(value = 5, message = "L'' utente deve avere almeno 5 anni")
    @Max(value = 99, message = "L'' utente deve avere al massimo 99 anni")
    private Integer age;

    private List<@NotBlank(message = "Il numero di telefono non può essere vuoto")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Il numero di telefono deve contenere solo numeri, e al massimo dieci cifre")
            String> phoneNumbers = new ArrayList<>();
}
