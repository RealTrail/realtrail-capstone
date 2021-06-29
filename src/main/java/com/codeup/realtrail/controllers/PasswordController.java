package com.codeup.realtrail.controllers;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.services.PasswordService;
import com.codeup.realtrail.services.UserService;
import com.codeup.realtrail.services.ValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {
    private UsersRepository usersDao;
    private UserService userService;
    private PasswordService passwordService;
    private ValidationService validationService;

    public PasswordController(UsersRepository usersDao, UserService userService, PasswordService passwordService, ValidationService validationService) {
        this.usersDao = usersDao;
        this.userService = userService;
        this.passwordService = passwordService;
        this.validationService = validationService;
    }

        @GetMapping("/password/reset")
    public String showResetPasswordForm() {

        return "users/resetPassword";


    }
    @PostMapping("/password/reset")
    public String resetPassword(@RequestParam(name = "oldPassword") String oldPassword,
                                @RequestParam(name = "newPassword") String newPassword,
                                @RequestParam(name = "confirmPassword") String confirmPassword,
                                Model model){
        User user = userService.getLoggedInUser();
        String message = "";
        boolean isPasswordSame = passwordService.check(oldPassword, user.getPassword());
        if(!isPasswordSame){
            message = "Password doesn't match. Please retry";
            model.addAttribute("message", message);
            return "users/resetPassword";
        }else if(validationService.passwordHasError(newPassword) || validationService.passwordHasError(newPassword)){
            message = "Password should be at least 8 digits long and must contain special characters";
            model.addAttribute("passwordErrorMessage", message);
            return "users/resetPassword";
        }else if(!newPassword.equals(confirmPassword)){
            message = "Not match";
            model.addAttribute("passwordConfirmMessage", message);
            return "users/resetPassword";
        }else{
            user.setPassword(passwordService.hash(newPassword));
            usersDao.save(user);
            return "redirect:/login";
        }


    }

    }
