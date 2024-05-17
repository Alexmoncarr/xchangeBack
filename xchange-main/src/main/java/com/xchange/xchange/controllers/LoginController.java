package com.xchange.xchange.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }
    
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    
    @RequestMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }
    
    @RequestMapping("/reset-password")
    public String resetPassword() {
        return "reset-password";
    }
    
    @RequestMapping("/change-password")
    public String changePassword() {
        return "change-password";
    }
    
  
}
