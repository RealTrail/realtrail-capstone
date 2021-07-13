package com.codeup.realtrail.controllers;

import com.codeup.realtrail.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request, Model model) {
//        String referrer = request.getHeader("referer");
//        System.out.println("referrer = " + referrer);
//
//        request.getSession().setAttribute("url_prior_login", referrer);
//
//        System.out.println("request.getSession().getAttribute(\"url_prior_login\") = " + request.getSession().getAttribute("url_prior_login"));
        model.addAttribute("user", new User());
        return "users/signup-login";
    }
}
