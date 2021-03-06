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
public class UsersIntegrationTests {
    private User testUser;
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
        testUser = usersDao.findByUsername("testUser");
        User testAdmin = usersDao.findByUsername("testAdmin");

        // Creates the test user if not exists
        if(testUser == null) {
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = usersDao.save(newUser);
        }

        // create the test admin if not exists
        if (testAdmin == null) {
            User newUser = new User("testAdmin", "testAdmin@realtrail.quest", "codeup");
            newUser.setAdmin(true);
            testAdmin = newUser;
        }

        // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
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
    public void testSaveUser() throws Exception {
        // make a post request to /signup and expect a redirection to /login
        this.mvc.perform(
                post("/signup").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("username", "user123")
                .param("email", "user123@codeup.com")
                .param("password", "codeup")
                .param("confirmCreatePassword", "codeup"))
            .andExpect(status().isOk());
    }

    @Test
    public void testShowHomePage_AsUser() throws Exception {
        Trail existingTrail = trailsDao.findAll().get(0);

        this.mvc.perform(get("/").with(user("testUser")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("RealTrail")))
                .andExpect(content().string(containsString(existingTrail.getName())));
    }

    @Test
    public void testShowContactUsPage() throws  Exception {

        this.mvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Karla Jara")));
    }


}
