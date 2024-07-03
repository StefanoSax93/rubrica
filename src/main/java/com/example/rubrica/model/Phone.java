package com.example.rubrica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il numero di telefono non può essere vuoto")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Il numero di telefono deve contenere solo numeri, e al massimo dieci cifre")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name="utente_id", nullable=false)
    @JsonBackReference
    private Utenti utenti;
}
