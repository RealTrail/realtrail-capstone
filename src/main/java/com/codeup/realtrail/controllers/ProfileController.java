package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.UserInterestRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.models.UserInterest;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.security.Security;
import java.util.List;

@Controller
public class ProfileController {
    private UsersRepository usersDao;
    private UserInterestRepository userInterestsDao;

    public ProfileController(UsersRepository usersDao, UserInterestRepository userInterestsDao) {
        this.usersDao = usersDao;
        this.userInterestsDao = userInterestsDao;
    }

    @GetMapping("/profile/create")
    public String getCreateProfileForm(Model model, Principal principal) {
        if (principal != null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // pass the user to create profile form
            model.addAttribute("user", user);
            model.addAttribute("interests", userInterestsDao.findAll());
            return "users/createProfile";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/profile/create")
    public String saveProfile(
            User user,
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "phoneNumber") String phoneNumber,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "state") String state,
            @RequestParam(name = "gender") String gender,
            @RequestParam(name = "interests") List<UserInterest> interests,
            Model model) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setCity(city);
        user.setState(state);
        user.setGender(gender);
        user.setInterests(interests);
        usersDao.save(user);
        model.addAttribute("user", user);

        return "users/showProfile";
    }

}
