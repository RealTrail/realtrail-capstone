package com.codeup.realtrail.controllers;

import com.codeup.realtrail.models.AjaxResponseBody;
import com.codeup.realtrail.daos.UserInterestRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.AjaxRequestBody;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProfileController {
    private UsersRepository usersDao;
    private UserInterestRepository userInterestsDao;
    private UserService userService;

    //Importing File Stack Api Key
    @Value("${filestack.api.key}")
    private String  filestackApi;

    public ProfileController(UsersRepository usersDao, UserInterestRepository userInterestsDao, UserService userService) {
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

        return "redirect:/profile";
    }

    // set up ajax post request response
    @PostMapping("/profile/image")
    public @ResponseBody ResponseEntity<?> uploadImageResultViaAjax(
            @Valid @RequestBody AjaxRequestBody requestBody) {
        String profileImageUrl = requestBody.getProfileImageUrl();

        AjaxResponseBody result = new AjaxResponseBody();

        if (profileImageUrl != null) {
            User user = userService.getLoggedInUser();
            user.setProfileImageUrl(profileImageUrl);
            usersDao.save(user);
            result.setUser(user);
            return ResponseEntity.ok(result);
        } else {
            result.setMsg("No image uploaded.");
            return ResponseEntity.badRequest().body(result);
        }
    }

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

    @GetMapping("/profile/settings/{id}")
    public String getAdminEditProfileForm(@PathVariable long id, Model model) {
        User user = usersDao.getById(id);
            // pass the user to create profile form to show prepopulated data in the form
            model.addAttribute("user", user);
            model.addAttribute("interests", userInterestsDao.findAll());
            model.addAttribute("fileStackApi", filestackApi);
            return "users/AdminProfile";

    }

    @PostMapping("/profile/settings/{id}")
    public String postAdminEditProfileForm(@PathVariable long id,@ModelAttribute User user, Model model) {
        User Newuser = usersDao.getById(id);
        System.out.println("user.getEmail() = " + user.getEmail());
        System.out.println("user.getPhoneNumber() = " + user.getPhoneNumber());
        Newuser.setFirstName(user.getFirstName());
        Newuser.setLastName(user.getLastName());
        Newuser.setGender(user.getGender());
        Newuser.setCity(user.getCity());
        Newuser.setState(user.getState());
        Newuser.setPhoneNumber(user.getPhoneNumber());
        Newuser.setInterests(user.getInterests());

        usersDao.save(Newuser);
        return "redirect:/profile/"+ id;
    }
    @GetMapping("/profile/delete/{id}")
    public String getAdminDeleteProfileForm(@PathVariable long id, Model model) {
       usersDao.delete(usersDao.getById(id));
        return "redirect:/users";
    }}