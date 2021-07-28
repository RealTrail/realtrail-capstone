package com.codeup.realtrail.controllers;

import com.codeup.realtrail.RealtrailApplication;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.Trail;
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

//Below: Importing static methods to utilize following curriculum example
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RealtrailApplication.class)
@AutoConfigureMockMvc
public class AdminRolesUsersIntegrationTests {

    private User testAdmin;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UsersRepository usersDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TrailsRepository trailsDao;

    @Before
    public void setup() throws Exception {
        testAdmin = usersDao.findByUsername("testAdmin");

        // create the test admin if not exists
        if (testAdmin == null) {
            User newUser = new User("testAdmin", "testAdmin@realtrail.quest", passwordEncoder.encode("pass"));
            newUser.setAdmin(true);
            testAdmin = usersDao.save(newUser);
        }

        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username", "testAdmin")
                .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/profile/settings"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void isTwo(){
        assertEquals(2,2);
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
    public void testShowHomePage_AsAdmin() throws Exception {
        Trail existingTrail = trailsDao.findAll().get(0);

        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("RealTrail")))
                .andExpect(content().string(containsString(existingTrail.getName())));
    }

    @Test
    public void testShowAllUsers() throws Exception {
        User existingUser = usersDao.findAll().get(0);

        // make a get request to /users and expect a redirection to all users page
        this.mvc.perform(get("/users"))
                .andExpect(status().is3xxRedirection())
                // Test the static content of the page
                .andExpect(content().string(containsString("All Users")))
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingUser.getUsername())));
    }
}
