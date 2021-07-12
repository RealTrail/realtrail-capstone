package com.codeup.realtrail;

import com.codeup.realtrail.models.User;
import com.codeup.realtrail.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    private UserService userService;

    public LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = userService.getLoggedInUser();

        if (user.getProfileImageUrl() == null) {
            response.sendRedirect("/profile/settings");
        } else {
            response.sendRedirect("/");
        }



//        HttpSession session = request.getSession();
//        String urlPriorLogin = request.getParameter("url_prior_login");
//        User user = userService.getLoggedInUser();
//
//        System.out.println("urlPriorLogin = " + urlPriorLogin);
//
//        if (user.getProfileImageUrl() == null) {
//            response.sendRedirect("/profile/settings");
//        } else if (session == null) {
//            response.sendRedirect("/");
//        } else {
//            String redirectUrl = (String) session.getAttribute("url_prior_login");
//
//            System.out.println("redirectUrl = " + redirectUrl);
//
//            if (redirectUrl != null && redirectUrl.matches("^https://localhost:8080.*$")) {
//                // clean this attribute from session
//                session.removeAttribute("url_prior_login");
//                // then redirect
//                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
//            } else {
//                response.sendRedirect("/");
//            }
//        }
    }
}
