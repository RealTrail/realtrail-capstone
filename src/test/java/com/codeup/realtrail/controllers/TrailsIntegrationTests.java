package com.codeup.realtrail.controllers;

import com.codeup.realtrail.RealtrailApplication;
import com.codeup.realtrail.daos.EventsRepository;
import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
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

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes  = RealtrailApplication.class)
@AutoConfigureMockMvc
public class TrailsIntegrationTests {
    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UsersRepository usersDao;

    @Autowired
    EventsRepository eventsDao;

    @Autowired
    TrailsRepository trailsDao;

    @Autowired
    MapPointsRepository mapPointsDao;

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
    public void testIfCreateTrailWorks() throws Exception {
        // Makes a Post request to /trails/create and expect a redirection to the Event
        this.mvc.perform(
                post("/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("trailName", "Republic Golf Course Trail - loop")
                .param("trailLength", "2.2")
                .param("difficultyLevel", "Easy")
                .param("trailType", "Loop")
                .param("trailDetails", "Republic Golf Course Trail.")
                .param("images", "https://www.texasgolftrails.com/wp-content/uploads/2017/06/Republic-1.jpg"))
            .andExpect(status().is3xxRedirection());
    }

}
