package com.example.subclub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainPageController {
    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @GetMapping("/secured")
    public String securedData() {
        return "securedData";
    }

    @GetMapping("/ad-panel")
    public String adminPanel() {
        return "Admin data";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }
}
