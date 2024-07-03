package com.example.rubrica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneDto {
    private Integer id;

    @NotBlank(message = "Il numero di telefono non può essere vuoto")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Il numero di telefono deve contenere solo numeri, e al massimo dieci cifre")
    private String phoneNumber;
}
