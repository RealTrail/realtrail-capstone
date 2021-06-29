package com.codeup.realtrail.controllers;
import com.codeup.realtrail.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class PasswordController {
    @GetMapping("/reset_password")
    public String showResetPasswordForm(Model model) {

        if (!showResetPasswordForm()) {
            model.addAttribute("message", "Invalid password");
            return "message";
        }
        return "redirect:/";
    }

    private boolean showResetPasswordForm() {
        return true;
    }}
