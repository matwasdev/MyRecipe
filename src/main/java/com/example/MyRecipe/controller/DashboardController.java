package com.example.MyRecipe.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        String usernameLoggedIn = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", usernameLoggedIn);

        return "dashboard";
    }



}
