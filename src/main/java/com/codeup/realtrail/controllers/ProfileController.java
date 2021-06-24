package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.UserInterestRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfileController {
    private UsersRepository usersDao;
    private UserInterestRepository userInterestsDao;
    private UserService userService;

    public ProfileController(UsersRepository usersDao, UserInterestRepository userInterestsDao, UserService userService) {
        this.usersDao = usersDao;
        this.userInterestsDao = userInterestsDao;
        this.userService = userService;
    }

    @GetMapping("/profile/settings")
    public String getCreateProfileForm(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.getLoggedInUser();

            // pass the user to create profile form to show prepopulated data in the form
            model.addAttribute("user", user);
            model.addAttribute("interests", userInterestsDao.findAll());
            return "users/profileSettings";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/profile/settings")
    public String saveProfile(@ModelAttribute User user, Model model) {
        // get the logged in user
        User loggedInUser = userService.getLoggedInUser();

        // set the loggedInUser info onto user(combine the loggedInUser with profile)
        user.setId(loggedInUser.getId());
        user.setEmail(loggedInUser.getEmail());
        user.setUsername(loggedInUser.getUsername());
        user.setPassword(loggedInUser.getPassword());

        System.out.println("user.getEmail() = " + user.getEmail());
        System.out.println("user.getPhoneNumber() = " + user.getPhoneNumber());

        usersDao.save(user);
        model.addAttribute("user", user);

        return "users/showProfile";
    }

//    @PostMapping("/profile/image")
//    public String uploadImage() {
//
//    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.getLoggedInUser();

            // pass the user to view/showProfile.html
            model.addAttribute("user", user);
            return "users/showProfile";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/profile/{id}")
    public String goToIndividualProfile(@PathVariable long id, Model model) {
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser.isAdmin()) {
            // get the searched user
            User searchedUser = usersDao.getById(id);
            model.addAttribute("user", searchedUser);
            return "users/showProfile";
        } else {
            return "error";
        }
    }
}
