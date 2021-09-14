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

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RealtrailApplication.class)
@AutoConfigureMockMvc
public class PasswordIntegrationTests {
    private User testPasswordUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UsersRepository usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {
        testPasswordUser = usersDao.findByUsername("testPasswordUser");

        // Create the test user if not exists
        if (testPasswordUser == null) {
            User newUser = new User();
            newUser.setUsername("testPasswordUser");
            newUser.setPassword(passwordEncoder.encode("!newPassword"));
            newUser.setEmail("testPasswordUser@codeup.com");
            testPasswordUser = usersDao.save(newUser);
        }

        // Throws a Post request to /login and expect a redirection to the home page after being logged in
        httpSession = this.mvc.perform(post("/login").with(csrf())
                        .param("username", "testPasswordUser")
                        .param("password", "!newPassword"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/profile/settings"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void contextLoads() {
        // Sanity Test, just to make sure the MVC bean is working
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        // It makes sure the returned session is not null
        assertNotNull(httpSession);
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
                        .param("oldPassword", "!newPassword")
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
                                .param("oldPassword", "!newPassword")
                                .param("newPassword", "!!password")
                                .param("confirmPassword", "confirm"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Not match! Please enter new password.")));
    }

    @Test
    public void testResetPassword_sameWithOldPassword() throws Exception {
        this.mvc.perform(
                        post("/password/reset").with(csrf())
                                .session((MockHttpSession) httpSession)
                                .param("oldPassword", "!newPassword")
                                .param("newPassword", "!newPassword")
                                .param("confirmPassword", "!newPassword"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Same password, please enter a new password.")));
    }

    @Test
    public void testResetPassword_success() throws Exception {
        this.mvc.perform(
                post("/password/reset").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("oldPassword", "!newPassword")
                        .param("newPassword", "!!password")
                        .param("confirmPassword", "!!password"))
                .andExpect(status().is3xxRedirection());
    }
}
