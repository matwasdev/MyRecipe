package com.example.MyRecipe.controller;

import com.example.MyRecipe.model.MyUser;
import com.example.MyRecipe.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {


    private MyUserRepository myUserRepository;
    private PasswordEncoder passwordEncoder;

    public LoginController(MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }


    @PostMapping("/login")
    public String loginSubmit(LoginForm loginForm, Model model) {
        Optional<MyUser> user = myUserRepository.findByUsername(loginForm.getUsername());
        if (user.isPresent() && passwordEncoder.matches(loginForm.getPassword(), user.get().getPassword())) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Nieprawidłowa nazwa użytkownika lub hasło.");
            return "login";
        }
    }


}