package com.codeup.realtrail.controllers;

import com.codeup.realtrail.RealtrailApplication;
import com.codeup.realtrail.daos.*;
import com.codeup.realtrail.models.Trail;
import com.codeup.realtrail.models.TrailComment;
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

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

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
    TrailCommentsRepository trailCommentsDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {
        testUser = usersDao.findByUsername("testTrailsUser");

        // Create the test user if not exists
        if (testUser == null) {
            User newUser = new User();
            newUser.setUsername("testTrailsUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testTrailsUser@codeup.com");
            testUser = usersDao.save(newUser);
        }

        // Throws a Post request to /login and expect a redirection to the home page after being logged in
        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username", "testTrailsUser")
                .param("password", "pass"))
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
    public void testIndividualTrailPage() throws Exception {
        Trail trail = trailsDao.findById(1);
        this.mvc.perform(get("/trails/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("Difficulty Level")))
                .andExpect(content().string(containsString(trail.getName())));
    }

    @Test
    public void testIfCreateTrailWorks() throws Exception {
        // Makes a Post request to /trails/create and expect a redirection to the Event
        this.mvc.perform(
                post("/trails/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("trailName", "Republic Golf Course Trail - loop")
                .param("trailLength", String.valueOf(2.2))
                .param("difficultyLevel", "Easy")
                .param("trailType", "Loop")
                .param("trailDetails", "Republic Golf Course Trail.")
                .param("images", "https://www.texasgolftrails.com/wp-content/uploads/2017/06/Republic-1.jpg"))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetSearchedTrail_noTrailFound() throws Exception {
        String keyword = "hello";
        this.mvc.perform(get("/search-trail?keyword=" + keyword))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("Submit")))
                .andExpect(content().string(containsString("No trail found.")));
    }

    @Test
    public void testGetSearchedTrail_trailsExist() throws Exception {
        String keyword = "trail";
        Trail searchedTrail = trailsDao.findByName(keyword).get(0);
        this.mvc.perform(get("/search-trail?keyword=trail"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Submit")))
                .andExpect(content().string(containsString(searchedTrail.getName())));
    }

    @Test
    public void testFilterDifficultyLevelOrType_difficultyLevel() throws Exception {
        String difficultyLevel = "Moderate";

        Trail searchedTrail = trailsDao.findByDifficultyLevel(difficultyLevel).get(0);
        this.mvc.perform(get("/trails/filter?difficulty=" + difficultyLevel + "&type="))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Submit")))
                .andExpect(content().string(containsString(searchedTrail.getName())));
    }

    @Test
    public void testFilterDifficultyLevelOrType_type() throws Exception {
        String type = "Point to point";
        Trail searchedTrail = trailsDao.findByType(type).get(0);
        System.out.println("searchedTrail.getName() = " + searchedTrail.getName());
        this.mvc.perform(get("/trails/filter?difficulty=&type=" + type))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Submit")))
                .andExpect(content().string(containsString(searchedTrail.getName())));
    }

    @Test
    public void testFilterDifficultyLevelOrType_difficultyLevelAndType() throws Exception {
        String difficultyLevel = "Easy";
        String type = "Out and back";
        Trail searchedTrail = trailsDao.findByDifficultyLevelAndType(difficultyLevel, type).get(0);
        this.mvc.perform(get("/trails/filter?difficulty=" + difficultyLevel + "&type=" + type))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Submit")))
                .andExpect(content().string(containsString(searchedTrail.getName())));
    }

    @Test
    public void testSaveTrailComment() throws Exception {
        this.mvc.perform(post("/trails/1/comment").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("rating", String.valueOf(5))
                .param("content", "This is a great trail!"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testDeleteTrailComment() throws Exception {
        // create a comment to be deleted
        this.mvc.perform(post("/trails/1/comment").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("rating", String.valueOf(4))
                        .param("content", "This is a good trail! I had fun."))
                .andExpect(status().is3xxRedirection());

        // get the recent comment that matches the content
        TrailComment trailComment = trailCommentsDao.getTrailCommentByContent("This is a good trail! I had fun.");

        // make a post request to /trails/1/comment/{id}/delete and expect a redirection back to the same trail page
        this.mvc.perform(post("/trails/1/comment/"+ trailComment.getId() + "/delete").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("id", String.valueOf(trailComment.getId())))
                .andExpect(status().is3xxRedirection());
    }
}
