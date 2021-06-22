package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
    private UsersRepository usersDao;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "users/signup-login";
    }

    @PostMapping("/login")
    public String loginSite(
            @RequestParam(name = "log_username") String username,
            @RequestParam(name = "password") String password,
            Model model) {
        User user = usersDao.findByUsername(username);

        if (user.getPassword().equals(password)) {
            return "users/createProfile";
        } else {
            return "redirect:/signup";
        }
    }

}
