package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.Trail;
import com.codeup.realtrail.services.EmailService;
import com.codeup.realtrail.services.UserService;
import com.codeup.realtrail.services.ValidationService;
import com.codeup.realtrail.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;
    private TrailsRepository trailsDao;
    private ValidationService validationService;
    private EmailService emailService;
    private UserService userService;

    public UserController(UsersRepository usersDao, PasswordEncoder passwordEncoder, TrailsRepository trailsDao, ValidationService validationService, EmailService emailService) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
        this.trailsDao = trailsDao;
        this.validationService = validationService;
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Trail> trailList =  trailsDao.findAll();
        model.addAttribute("trails", trailList);
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
        // error message for the username entry
        String message = "";
        if(validationService.usernameHasError(user.getUsername())){
            message = "Username must be less than 50 letters, can include numbers, and contain no special characters";
            model.addAttribute("message", message);
            return "users/signup-login";
        }

         // error message for email entry
        if(validationService.emailHasError(user.getEmail())){
            message = "Must enter email in the correct format";
            model.addAttribute("emailMessage", message);
            return "users/signup-login";
        }

        //error message for password
        if(validationService.passwordHasError(user.getPassword())){
            message = "Password should be at least 8 digits long and must contain special characters";
            model.addAttribute("passwordMessage", message);
            return "users/signup-login";
        }

        // check if username and email exist in db
        if (usersDao.findByUsername(user.getUsername()) != null) {
            message = "Username already exist, please enter another one!";
            model.addAttribute("message", message);
            return "users/signup-login";
        } else if (usersDao.findByEmail(user.getEmail()) != null) {
            message = "Email already exist, please enter another one!";
            model.addAttribute("emailMessage", message);
            return "users/signup-login";
        } else {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            usersDao.save(user);
            emailService.Send(user, "Your email had been verified!", user.getEmail());
            return "redirect:/login";
        }
    }


}
