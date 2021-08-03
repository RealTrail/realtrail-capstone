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

    @Test
    public void testCreateEvent_customizeTrail() throws Exception {
        this.mvc.perform(
                post("/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("name", "testUser's event")
                        .param("eventDate", "2021-08-21")
                        .param("eventTime", "10:00")
                        .param("meetLocation", "Entrance")
                        .param("eventMeetTime", "09:45")
                        .param("eventDetails", "Let's go hiking together!")
                        .param("trailOption", "customize trail")
                        .param("createdTrailId", "29")
                        .param("trailPoint", "-98.40787943048224,29.352720932080558")
                        .param("createdCoordinates", "-98.40969155750705,29.35787004055689;-98.40986832216294,29.356928536589166;-98.40762930318897,29.35759614939164;-98.40474214714357,29.35240919640988;-98.40298780826778,29.352399504449764;-98.4043796743005,29.349083424755563;-98.40809131705562,29.349865274406554;-98.4107822580531,29.353828351045507;-98.41109156161595,29.357279067728015;-98.40982341700813,29.35692860964778"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testShowEditEvent() throws Exception {
        Event event = eventsDao.getByOwner(testUser).get(0);
        this.mvc.perform(get("/events/" + event.getId() + "/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString("Event coordinator")))
                .andExpect(content().string(containsString(event.getName())));
    }

    @Test
    public void testUpdateEvent() {
        // get the first event testUser has created
        Event event = eventsDao.getByOwner(testUser).get(0);

        // make a post request to /events/{id}/edit and expect a redirection to the same individual event page

    }
}
