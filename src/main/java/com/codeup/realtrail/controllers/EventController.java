package com.codeup.realtrail.controllers;


import com.codeup.realtrail.daos.EventsRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import services.StringService;

import java.util.List;

@Controller
public class EventController {
    private EventsRepository eventsDao;
    private UsersRepository usersDao;
    private User user;

    public EventController(EventsRepository eventsRepository, UsersRepository usersRepository){
        eventsDao = eventsRepository;
        usersDao = usersRepository;
    }

    // showAllEvents.html page
    @GetMapping("/events")
    public String eventsIndex(Model model){
        List<Event> eventsList = eventsDao.findAll();
        model.addAttribute("noEventsFound", eventsList.size() == 0);
        model.addAttribute("events", eventsList);
        return "events/showAllEvents";
    }

    // showEvent.html page
    @GetMapping("/events/{id}")
    public String id(@PathVariable Long id, Model model){
        Event event= eventsDao.getById(id);
        model.addAttribute("eventId", id);
        model.addAttribute("event", event);
        return "/events/show";
    }

    @GetMapping("event/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model){
        model.addAttribute("eventEditedPost",eventsDao.getById(id));
        return "events/edit";
    }

}
