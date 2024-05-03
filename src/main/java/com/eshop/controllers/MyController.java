package com.eshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {
    @GetMapping("/home")
    public String homePage() {
        System.out.println("edw home");
        return "html/home.html";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user")
    public String userPage() {
        System.out.println("this is user page");
        return "user";
    }

    @GetMapping("/profile")
    public String profilePage() {
        System.out.println("this is profile");
        return "redirect:/html/profile.html";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/home?logout";  // Redirect to the login page with a logout parameter
    }

    @GetMapping("/authenticated")
    public String authenticated() {
        System.out.println("edw authentication");
        return "redirect:/html/authenticated.html";
    }
}


