package com.zobaier.SpringOath2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RestController
public class HelloController {
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null)
            return "No user logged in";

        // Tries "name" (Google) first, then "login" (GitHub)
        String name = principal.getAttribute("name");
        if (name == null) {
            name = principal.getAttribute("login");
        }

        return "Hello, " + name;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Security OAuth2 with Google and GitHub!";
    }

    @GetMapping("/secured")
    public String secured() {
        return "You have successfully authenticated!";
    }
}