package com.codeup.realtrail.services;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean usernameHasError(String username) {
        boolean inputHasErrors = username.isEmpty() || (username.length() > 50)
                || username.contains("@") || username.contains("#")
                || username.contains("!") || username.contains("~")
                || username.contains("$") || username.contains("%")
                || username.contains("^") || username.contains("&")
                || username.contains("*") || username.contains("(")
                || username.contains(")") || username.contains("-")
                || username.contains("+") || username.contains("/")
                || username.contains(":") || username.contains(".")
                || username.contains(", ") || username.contains("<")
                || username.contains(">") || username.contains("?")
                || username.contains("|");
        return inputHasErrors;
    }

    public boolean emailHasError(String email) {
        return email.isEmpty() || (!email.contains("@"));
    }


    public boolean passwordHasError(String password) {
        boolean inputHasError = password.isEmpty() || (password.length()<8)
                || (!(password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|")));

        return inputHasError;
    }
}
