package com.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/adminLogin")
    public String adminLogin() {
        return "adminLogin";
    }
}
