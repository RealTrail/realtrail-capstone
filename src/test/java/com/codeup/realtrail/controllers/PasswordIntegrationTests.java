package com.codeup.realtrail.controllers;

import com.codeup.realtrail.RealtrailApplication;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RealtrailApplication.class)
@AutoConfigureMockMvc
public class PasswordIntegrationTests {
    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UsersRepository usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {
        testUser = usersDao.findByUsername("testUser");

        // Create the test user if not exists
        if (testUser == null) {
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = usersDao.save(newUser);
        }

        // Throws a Post request to /login and expect a redirection to the home page after being logged in
        httpSession = this.mvc.perform(post("/login").with(csrf())
                        .param("username", "testUser")
                        .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void testShowResetPasswordFrom() throws Exception {
        this.mvc.perform(get("/password/reset"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Password Reset")));
    }

    @Test
    public void testResetPassword_oldPasswordNotMatch() throws Exception {
        this.mvc.perform(
                        post("/password/reset").with(csrf())
                                .session((MockHttpSession) httpSession)
                                .param("oldPassword", "old")
                                .param("newPassword", "newPassword")
                                .param("confirmPassword", "newPassword"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Password doesn&#39;t match. Please try again.")));
    }

    @Test
    public void testResetPassword_wrongPasswordFormat() throws Exception {
        this.mvc.perform(
                post("/password/reset").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("oldPassword", "pass")
                        .param("newPassword", "newPassword")
                        .param("confirmPassword", "newPassword"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Password should be at least 8 digits long and must contain special characters")));
    }

    @Test
    public void testResetPassword_confirmPasswordNotMatch() throws Exception {
        this.mvc.perform(
                        post("/password/reset").with(csrf())
                                .session((MockHttpSession) httpSession)
                                .param("oldPassword", "pass")
                                .param("newPassword", "!newPassword")
                                .param("confirmPassword", "confirm"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Not match! Please enter new password.")));
    }
}
