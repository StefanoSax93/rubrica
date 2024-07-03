package com.example.rubrica.controller;

import com.example.rubrica.dto.UtentiDto;
import com.example.rubrica.model.Utenti;
import com.example.rubrica.service.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rubrica/utenti")
public class UtentiRestController {

    @Autowired
    private UtentiService utentiService;

    @GetMapping()
    public List<Utenti> getAllUtenti() {
          return utentiService.getAllUtenti();
    }

    @GetMapping("/utente/{id}")
    public Utenti getUtente(@PathVariable Integer id) {
        return utentiService.getUtente(id);
    }

    @PostMapping("/add")
    public Utenti addUtente(@RequestBody UtentiDto utente) {
        return utentiService.addUtente(utente);
    }

    @PutMapping("/update")
    public Utenti updateUtente(@RequestBody UtentiDto utente) {
        return utentiService.updateUtente(utente);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUtente(@PathVariable Integer id)  {
        utentiService.deleteUtente(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll()  {
        utentiService.deleteAll();
    }
}

