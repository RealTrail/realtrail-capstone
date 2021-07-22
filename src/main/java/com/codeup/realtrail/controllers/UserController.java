package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.EventsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.daos.UserInterestsRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.*;
import com.codeup.realtrail.services.EmailService;
import com.codeup.realtrail.services.UserService;
import com.codeup.realtrail.services.ValidationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;
    private TrailsRepository trailsDao;
    private EventsRepository eventsDao;
    private UserInterestsRepository interestsDao;
    private ValidationService validationService;
    private EmailService emailService;
    private UserService userService;

    public UserController(UsersRepository usersDao, PasswordEncoder passwordEncoder, TrailsRepository trailsDao, EventsRepository eventsDao, UserInterestsRepository interestsDao, ValidationService validationService, EmailService emailService, UserService userService) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
        this.trailsDao = trailsDao;
        this.eventsDao = eventsDao;
        this.interestsDao = interestsDao;
        this.validationService = validationService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Trail> trailList =  trailsDao.findAll();
        List<Event> events = eventsDao.findAll();

        model.addAttribute("title", "RealTrail Home");
        model.addAttribute("trails", trailList);
        model.addAttribute("events", events);
        return "home";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("title", "Signup");
        model.addAttribute("user", new User());
        return "users/signup-login";
    }

    // This is saving the user to the database
    @PostMapping("/signup")
    public String saveUser(@ModelAttribute User user, @RequestParam(name = "confirmCreatePassword") String confirmPassword, Model model) {
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

        //check if password and confirmPassword are the same
        if (!confirmPassword.equals(user.getPassword())) {
            message = "Not match!";
            model.addAttribute("passwordNotMatch", message);
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

    @GetMapping("/users")
    public String showAllUsers(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.getLoggedInUser();
            if (user.isAdmin()) {
                List<User> users = usersDao.findAll();

                model.addAttribute("title", "View All Users");
                model.addAttribute("users", users);
                model.addAttribute("loggedInUser", user);
                return "users/allUsers";
            } else {
                return "error";
            }
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable long id) {
        System.out.println("id = " + id);
        System.out.println("usersDao.getById(id).getUsername() = " + usersDao.getById(id).getUsername());
        User user = usersDao.getById(id);
        List<UserInterest> interests = user.getInterests();
        List<Event> events = user.getEvents();

        // remove the user need to delete from users_interests table
        for (UserInterest interest : interests) {
            List<User> users = interest.getUsers();
            users.remove(user);
            interest.setUsers(users);
            interestsDao.save(interest);
        }

        // remove the user need to delete from events_participants table
        for (Event event : events) {
            List<User> participants = event.getParticipants();
            participants.remove(user);
            event.setParticipants(participants);
            eventsDao.save(event);
        }

        user.setInterests(null);
        user.setEvents(null);

        usersDao.delete(usersDao.getById(id));
        return "redirect:/users";
    }

    @GetMapping("/contact")
    public String showContactUsPage(Model model) {
        model.addAttribute("title", "Contact Us");
        return "contactUs";
    }
}
