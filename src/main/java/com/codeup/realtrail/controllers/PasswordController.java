package com.codeup.realtrail.controllers;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.services.PasswordService;
import com.codeup.realtrail.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {
    private UserService userService;
    private PasswordService passwordService;

    public PasswordController(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }

    //    @GetMapping("/password/reset")
//    public String showResetPasswordForm() {
//
//        return "users/resetPassword";
//
//
//    }
    @PostMapping("/password/reset")
    public String resetPassword(@RequestParam(name = "oldPassword") String oldPassword,
                                @RequestParam(name = "newPassword") String newPassword,
                                @RequestParam(name = "confirmPassword") String confirmPassword,
                                Model model){
        User user = userService.getLoggedInUser();

    }

    }
