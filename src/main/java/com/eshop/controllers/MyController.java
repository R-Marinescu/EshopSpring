package com.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {
    @GetMapping("/home")
    public String homePage() {
        System.out.println("edw home");
        return "home";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user")
    public String userPage() {
        System.out.println("oooooo");
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("edw login");
        return "login";
    }

    @GetMapping("/authenticated")
    public String authenticated() {
        System.out.println("edw authenticated");
        return "authenticated";
    }
}


