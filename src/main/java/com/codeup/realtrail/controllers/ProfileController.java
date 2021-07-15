package com.codeup.realtrail.controllers;

import com.codeup.realtrail.models.*;
import com.codeup.realtrail.daos.UserInterestsRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {
    private UsersRepository usersDao;
    private UserInterestsRepository userInterestsDao;
    private UserService userService;

    //Importing File Stack Api Key
    @Value("${filestack.api.key}")
    private String  filestackApi;

    public ProfileController(UsersRepository usersDao, UserInterestsRepository userInterestsDao, UserService userService) {
        this.usersDao = usersDao;
        this.userInterestsDao = userInterestsDao;
        this.userService = userService;
    }

    @GetMapping("/profile/settings")
    public String getCreateProfileForm(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.getLoggedInUser();

            // save a default image to db if the profileImageUrl == null
            if (user.getProfileImageUrl() == null) {
                user.setProfileImageUrl("/images/default-profile.png");
                usersDao.save(user);

                // get the user with default profile image
                user = userService.getLoggedInUser();
            }

            // pass the user to create profile form to show prepopulated data in the form
            model.addAttribute("user", user);
            model.addAttribute("interests", userInterestsDao.findAll());
            model.addAttribute("fileStackApi", filestackApi);
            model.addAttribute("postUrl", "/profile/settings");
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

        // if user doesn't change profile image, set profile image from db back to user
        if (user.getProfileImageUrl().isEmpty()) {
            user.setProfileImageUrl(loggedInUser.getProfileImageUrl());
        }

        if (loggedInUser.isAdmin()) {
            user.setAdmin(true);
        }

        usersDao.save(user);
        model.addAttribute("user", user);

        return "redirect:/profile";
    }

    // set up ajax post request response
    @RequestMapping(value = "/profile/image", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> uploadProfileImage(@RequestBody ProfileImageUpdateRequest profileImageUpdateRequest) {
        String profileImageUrl = profileImageUpdateRequest.getProfileImageUrl();

        AjaxResponseBody response = new AjaxResponseBody();

        if (profileImageUrl != null) {
            User loggedInUser = userService.getLoggedInUser();
            loggedInUser.setProfileImageUrl(profileImageUrl);
            usersDao.save(loggedInUser);
            response.setUser(loggedInUser);
            return ResponseEntity.ok(response);
        } else {
            response.setMsg("No image uploaded.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.getLoggedInUser();
            List<Event> createdEvents = user.getCreatedEvents();
            List<Event> events = user.getEvents();
            model.addAttribute("user", user);
            model.addAttribute("createdEvents", createdEvents);
            model.addAttribute("events", events);
            return "users/showProfile";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/profile/{id}/edit")
    public String getAdminEditProfileForm(@PathVariable long id, Model model) {
        User user = usersDao.getById(id);
        // pass the user to create profile form to show prepopulated data in the form
        model.addAttribute("user", user);
        model.addAttribute("interests", userInterestsDao.findAll());
        model.addAttribute("fileStackApi", filestackApi);
        model.addAttribute("postUrl", "/profile/" + id + "/edit");
        return "users/adminProfileForm";
    }

    @PostMapping("/profile/{id}/edit")
    public String editUsersProfile(@PathVariable long id, @ModelAttribute User user) {
        User userFromDB =  usersDao.getById(id);
        user.setId(id);
        user.setUsername(userFromDB.getUsername());
        user.setEmail(userFromDB.getEmail());
        user.setPassword(userFromDB.getPassword());
        if (user.getProfileImageUrl().isEmpty()) {
            user.setProfileImageUrl(userFromDB.getProfileImageUrl());
        }
        if (userFromDB.isAdmin()) {
            user.setAdmin(true);
        }
        usersDao.save(user);
        return "redirect:/profile/" + id;
    }

    @GetMapping("/profile/{id}")
    public String goToIndividualProfile(@PathVariable long id, Model model) {
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser.isAdmin()) {
            // get the searched user
            User searchedUser = usersDao.getById(id);
            List<Event> createdEvents = searchedUser.getCreatedEvents();
            List<Event> events = searchedUser.getEvents();
            model.addAttribute("user", searchedUser);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("createdEvents", createdEvents);
            model.addAttribute("events", events);
            return "users/profile";
        } else {
            return "error";
        }
    }
}