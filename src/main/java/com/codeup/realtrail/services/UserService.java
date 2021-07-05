package com.codeup.realtrail.services;

import com.codeup.realtrail.daos.EventsRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UsersRepository usersDao;
    private EventsRepository eventsDao;

    public UserService(UsersRepository usersDao, EventsRepository eventsDao) {
        this.usersDao = usersDao;
        this.eventsDao = eventsDao;
    }

    public User getLoggedInUser() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usersDao.findById(loggedInUser.getId()).get();
    }

    public Event getLoggedInOwner(){
        User loggedInOwner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return eventsDao.findById(loggedInOwner.getId()).get();
    }

}
