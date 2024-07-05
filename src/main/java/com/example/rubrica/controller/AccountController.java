package com.example.rubrica.controller;

import com.example.rubrica.dto.RegisterDto;
import com.example.rubrica.model.AppUser;
import com.example.rubrica.model.Utenti;
import com.example.rubrica.repository.UserRepository;
import com.example.rubrica.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Integer userId = userService.getUserIdByEmail(email);
        if (userId != null) {
            AppUser user = userService.getProfile(userId);
            model.addAttribute("user", user);
            return "profile";
        } else {
            return "error";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success",false);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterDto registerDto, BindingResult result) {
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(
                    new FieldError("registerDto","confirmPassword","Le password non coincidono")
            );
        }

        AppUser user = repo.findByEmail(registerDto.getEmail());
        if(user != null) {
            result.addError(
                    new FieldError("registerDto","email","L'indirizzo email esiste già")
            );
        }

        if(result.hasErrors()) {
            return "register";
        }

        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            AppUser newUser = new AppUser();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setAge(registerDto.getAge());
            newUser.setEmail(registerDto.getEmail());
            newUser.setRole("client");
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            repo.save(newUser);

            // dobbiamo pulire il form di registrazione
            model.addAttribute("registerDto",new RegisterDto());
            model.addAttribute("success",true);
        } catch(Exception ex) {
            result.addError(
                    new FieldError("registerDto","firstName",ex.getMessage())
            );
        }
        return "register";
    }
}
