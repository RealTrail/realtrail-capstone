package com.codeup.realtrail.controllers;

import com.codeup.realtrail.RealtrailApplication;
import com.codeup.realtrail.daos.*;
import com.codeup.realtrail.models.Event;
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
public class EventsIntegrationTests {
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
    EventCommentsRepository eventCommentsDao;

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

        System.out.println("httpSession = " + httpSession);
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
    public void testShowEventsPage() throws Exception {
        Event existingEvent = eventsDao.findAll().get(0);

        // Makes a Get request to /events and verifies that we get some of the static text of the events/showAllEvents.html template and at least the name from the first Event is present in the template.
        this.mvc.perform(get("/events"))
                .andExpect(status().isOk())
                // Test the static content of the page
                .andExpect(content().string(containsString("Review the list for trail events happening near you!")))
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingEvent.getName())));
    }

    @Test
    public void testCreateEvent_existingTrail() throws Exception {

        // Makes a Post request to /create and expect a redirection to the Event
        this.mvc.perform(
                post("/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("name", "testUser's event")
                .param("eventDate", "2021-08-21")
                .param("eventTime", "10:00")
                .param("meetLocation", "Entrance")
                .param("eventMeetTime", "09:45")
                .param("eventDetails", "Let's go hiking together!")
                .param("trailOption", "existing trail")
                .param("trailOptions", "1"))
            .andExpect(status().is3xxRedirection());
    }
}
