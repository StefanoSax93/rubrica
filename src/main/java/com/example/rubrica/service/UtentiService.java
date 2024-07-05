package com.example.rubrica.service;

import com.example.rubrica.dto.UtentiDto;
import com.example.rubrica.model.Phone;
import com.example.rubrica.model.Utenti;
import com.example.rubrica.repository.PhoneRepository;
import com.example.rubrica.repository.UtentiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UtentiService {

    @Autowired
    private UtentiRepository utentiRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public List<Utenti> getAllUtenti() {
        return utentiRepository.findAll();
    }

    public Utenti getUtente(Integer id) {
        return utentiRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Utenti addUtente(UtentiDto utenteDto) {
        Utenti utente = new Utenti();
        utente.setName(utenteDto.getName());
        utente.setAge(utenteDto.getAge());
        utente.setEmail(utenteDto.getEmail());

        for (String phoneNumber : utenteDto.getPhoneNumbers()) {
            Phone phone = new Phone();
            phone.setPhoneNumber(phoneNumber);
            phone.setUtenti(utente);
            utente.getPhoneNumber().add(phone);
        }

        return utentiRepository.save(utente);
    }

    public Utenti updateUtente(UtentiDto utenteDto) {
        Optional<Utenti> optionalUtente = utentiRepository.findById(utenteDto.getId());

        if (optionalUtente.isPresent()) {
            Utenti utente = optionalUtente.get();
            utente.setName(utenteDto.getName());
            utente.setAge(utenteDto.getAge());
            utente.setEmail(utenteDto.getEmail());

            utente.getPhoneNumber().clear();
            for (String phoneNumber : utenteDto.getPhoneNumbers()) {
                Phone phone = new Phone();
                phone.setPhoneNumber(phoneNumber);
                phone.setUtenti(utente);
                utente.getPhoneNumber().add(phone);
            }

            return utentiRepository.save(utente);
        } else {
            throw new RuntimeException("Utente non trovato con id: " + utenteDto.getId());
        }
    }

    public void deleteUtente(Integer id) {
        utentiRepository.deleteById(id);
    }

    public void deleteAll() {
        utentiRepository.deleteAll();
    }
}
