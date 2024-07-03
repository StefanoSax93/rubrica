package com.example.rubrica.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Utenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome non può essere vuoto o una stringa vuota")
    private String name;

    @NotNull(message = "Il campo età non può essere nullo")
    @Min(value = 5, message = "L'' utente deve avere almeno 5 anni")
    @Max(value = 99, message = "L'' utente deve avere al massimo 99 anni")
    private Integer age;

    @OneToMany(mappedBy="utenti", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id")
    @JsonManagedReference
    private Set<Phone> phoneNumber = new HashSet<>();
}
