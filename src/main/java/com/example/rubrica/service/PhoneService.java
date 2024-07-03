package com.example.rubrica.service;

import com.example.rubrica.dto.PhoneDto;
import com.example.rubrica.model.Phone;
import com.example.rubrica.model.Utenti;
import com.example.rubrica.repository.PhoneRepository;
import com.example.rubrica.repository.UtentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneService {
    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    UtentiRepository utentiRepository;

    public Phone getPhone(Integer id) {
        return phoneRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void addPhoneNumber(Integer utenteId, Phone phone) {
        Utenti utente = utentiRepository.findById(utenteId).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        Phone newPhone = new Phone();
        newPhone.setPhoneNumber(phone.getPhoneNumber());
        newPhone.setUtenti(utente);
        phoneRepository.save(newPhone);
    }

    public void updatePhone(PhoneDto phone) {
        Optional<Phone> optionalPhone = phoneRepository.findById(phone.getId());

        if (optionalPhone.isPresent()) {
            Phone existingPhone = optionalPhone.get();
            existingPhone.setPhoneNumber(phone.getPhoneNumber());
            phoneRepository.save(existingPhone);
        } else {
            throw new RuntimeException("Numero non trovato con id: " + phone.getId());
        }
    }

    public void deleteNumero(Integer id) {
        phoneRepository.deleteById(id);
    }
}
