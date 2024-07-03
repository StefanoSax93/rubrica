package com.example.rubrica.controller;

import com.example.rubrica.dto.PhoneDto;
import com.example.rubrica.model.Phone;
import com.example.rubrica.service.PhoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @GetMapping("/{utenteId}/creaNuovoNumero")
    public String mostraFormNuovoNumero(@PathVariable("utenteId") Integer utenteId, Model model) {
        model.addAttribute("utenteId", utenteId);
        model.addAttribute("phone", new Phone());
        return "creaNuovoNumero";
    }

    @PostMapping("/{utenteId}/aggiungiNumero")
    public String aggiungiNumero(@PathVariable("utenteId") Integer utenteId,
                                 @Valid @ModelAttribute("phone") Phone phoneNumber,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "creaNuovoNumero";
        }
        phoneService.addPhoneNumber(utenteId,phoneNumber);
        return "redirect:/rubrica/utente/" + utenteId;
    }

    @GetMapping("/modifica/{id}")
    public String modificaPhone(@PathVariable Integer id,Model model) {
        Phone phone = phoneService.getPhone(id);
        PhoneDto phoneDTO = new PhoneDto();
        phoneDTO.setId(phone.getId());
        phoneDTO.setPhoneNumber(phone.getPhoneNumber());
        model.addAttribute("phone", phoneDTO);
        return "modificaNumero";
    }

    @PostMapping("/update/{id}")
    public String updatePhone(@PathVariable Integer id, @Valid @ModelAttribute("phone") PhoneDto phone, BindingResult result) {
        if (result.hasErrors()) {
            return "modificaNumero";
        }

        phoneService.updatePhone(phone);
        Phone phones = phoneService.getPhone(id);
        return "redirect:/rubrica/utente/" + phones.getUtenti().getId();
    }

    @PostMapping("/elimina/{id}")
    public String deleteNumero(@PathVariable Integer id)  {
        Phone phone = phoneService.getPhone(id);
        phoneService.deleteNumero(id);
        return "redirect:/rubrica/utente/" + phone.getUtenti().getId();
    }
}
