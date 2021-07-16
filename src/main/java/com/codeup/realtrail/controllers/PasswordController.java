package com.codeup.realtrail.controllers;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.services.UserService;
import com.codeup.realtrail.services.ValidationService;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {
    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private ValidationService validationService;

    public PasswordController(UsersRepository usersDao, PasswordEncoder passwordEncoder, UserService userService, ValidationService validationService) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
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
        boolean isPasswordSame = passwordEncoder.matches(oldPassword, user.getPassword());
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
            user.setPassword(passwordEncoder.encode(newPassword));
            usersDao.save(user);
            return "redirect:/login";
        }
    }
}
