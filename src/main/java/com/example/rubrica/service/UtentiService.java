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

        Phone phone = new Phone();
        phone.setPhoneNumber(utenteDto.getPhoneNumber());
        phone.setUtenti(utente);

        utente.getPhoneNumber().add(phone);
        utentiRepository.save(utente);
        phoneRepository.save(phone);

        return utente;
    }

    public Utenti updateUtente(UtentiDto utenteDto) {
        Optional<Utenti> optionalUtente = utentiRepository.findById(utenteDto.getId());

        if (optionalUtente.isPresent()) {
            Utenti utente = optionalUtente.get();

            utente.setName(utenteDto.getName());
            utente.setAge(utenteDto.getAge());

            Set<Phone> phones = utente.getPhoneNumber();
            if (!phones.isEmpty()) {
                Phone firstPhone = phones.stream().findFirst().orElse(null);
                firstPhone.setPhoneNumber(utenteDto.getPhoneNumber());
                phoneRepository.save(firstPhone);
            } else {
                Phone newPhone = new Phone();
                newPhone.setPhoneNumber(utenteDto.getPhoneNumber());
                newPhone.setUtenti(utente);
                phones.add(newPhone);
                phoneRepository.save(newPhone);
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
