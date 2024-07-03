package com.example.rubrica.controller;

import com.example.rubrica.dto.UtentiDto;
import com.example.rubrica.model.Utenti;
import com.example.rubrica.service.UtentiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/rubrica")
public class UtentiController {

    @Autowired
    private UtentiService utentiService;

    @GetMapping()
    public String getAllUtenti(Model model) {
          List<Utenti> listaUtenti = utentiService.getAllUtenti();
          model.addAttribute("listaUtenti",listaUtenti);
          return "rubrica";
    }

    @GetMapping("/creaUtente")
    public String creaUtente(Model model) {
        model.addAttribute("utente", new Utenti());
        return "creaUtente";
    }

    @PostMapping("/nuovoUtente")
    public String addUtente(@Valid @ModelAttribute("utente") UtentiDto utente, BindingResult result) {
        if (result.hasErrors()) {
            return "creaUtente";
        }
        utentiService.addUtente(utente);
        return "redirect:/rubrica";
    }

    @GetMapping("/utente/{id}")
    public String getUtente(@PathVariable Integer id,Model model) {
        Utenti utente = utentiService.getUtente(id);
        model.addAttribute("utente",utente);
        return "dettaglioUtente";
    }


    @GetMapping("/modificaUtente/{id}")
    public String modificaUtente(@PathVariable Integer id,Model model) {
        Utenti utente = utentiService.getUtente(id);
        UtentiDto user = new UtentiDto();
        user.setId(utente.getId());
        user.setName(utente.getName());
        user.setAge(utente.getAge());
        user.setPhoneNumber(String.valueOf(Objects.requireNonNull(utente.getPhoneNumber().stream().findFirst().orElse(null)).getPhoneNumber()));
        model.addAttribute("utente", user);
        return "modificaUtente";
    }

    @PostMapping("/update/{id}")
    public String updateUtente(@PathVariable Integer id, @Valid @ModelAttribute("utente") UtentiDto utente, BindingResult result) {
        if (result.hasErrors()) {
            return "modificaUtente";
        }

        utentiService.updateUtente(utente);
        return "redirect:/rubrica/utente/" + id;
    }

    @PostMapping("/elimina/{id}")
    public String deleteUtente(@PathVariable Integer id)  {
        utentiService.deleteUtente(id);
        return "redirect:/rubrica";
    }

    @PostMapping("/deleteAll")
    public String deleteAll()  {
        utentiService.deleteAll();
        return "redirect:/rubrica";
    }
}

