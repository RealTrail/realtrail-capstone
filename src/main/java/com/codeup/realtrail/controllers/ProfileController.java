package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.UserInterestRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.models.UserInterest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getCreateProfileForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("interests", userInterestsDao.findAll());
        return "users/createProfile";
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

//        return "redirect:/users/profile" + user.getId();
        return "users/showProfile";
    }
}
