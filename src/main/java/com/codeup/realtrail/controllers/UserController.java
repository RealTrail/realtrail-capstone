package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UsersRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/signup-login";
    }

    // This is saving the user to the database
    @PostMapping("/signup")
    public String saveUser(@ModelAttribute User user, Model model) {
        String message = "";
        if(user.getUsername().length() > 50){
            message = "Username is too long!";
        }
        model.addAttribute("message", message);
        return "redirect:/signup";


//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        usersDao.save(user);
//        return "redirect:/login";
    }







}
