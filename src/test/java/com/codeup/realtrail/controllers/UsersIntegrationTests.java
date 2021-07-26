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

    @Before
    public void setup() throws Exception {
        testUser = usersDao.findByUsername("testUser");

        // Creates the test user if not exists
        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = usersDao.save(newUser);

            // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
            httpSession = this.mvc.perform(post("/login").with(csrf())
                    .param("username", "testUser")
                    .param("password", "pass"))
                    .andExpect(status().is(HttpStatus.FOUND.value()))
                    .andExpect(redirectedUrl("/profile/settings"))
                    .andReturn()
                    .getRequest()
                    .getSession();
        }
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

}
