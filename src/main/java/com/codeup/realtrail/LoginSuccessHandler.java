package com.codeup.realtrail;

import com.codeup.realtrail.models.User;
import com.codeup.realtrail.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private UserService userService;

    public LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = userService.getLoggedInUser();
        if (user.getPhoneNumber() == null) {
            response.sendRedirect("/profile/settings");
        } else {
            response.sendRedirect("/");
        }

    }


}
