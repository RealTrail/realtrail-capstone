package com.codeup.realtrail.models;

import com.codeup.realtrail.daos.UsersRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTest {

    private User testUser;

    @Autowired
    UsersRepository usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("This executes before any test cases.");
    }

    @Before
    public void setup() {
        testUser = usersDao.findByUsername("testUser");

        // Create the test user if not exists
        if (testUser == null) {
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = usersDao.save(newUser);
        }
    }
}
