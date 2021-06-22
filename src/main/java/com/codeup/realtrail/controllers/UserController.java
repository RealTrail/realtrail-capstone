package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.UserInterestRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.models.UserInterest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private UsersRepository usersDao;
    private UserInterestRepository userInterestsDao;

    public UserController(UsersRepository usersDao, UserInterestRepository userInterestsDao) {
        this.usersDao = usersDao;
        this.userInterestsDao = userInterestsDao;
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


    @PostMapping("/signup")
    public String saveUser(@ModelAttribute User user) {
        usersDao.save(user);
        System.out.println("user.getUsername() = " + user.getUsername());
        return "redirect:/login";
    }



    @GetMapping("/profile/edit")
    public String showEditProfileForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("interests", userInterestDao.findAll());
        return "users/editProfile";
    }

}
