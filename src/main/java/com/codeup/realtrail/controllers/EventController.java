package com.codeup.realtrail.controllers;


import com.codeup.realtrail.daos.EventsRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.*;
//import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.StringService;

import java.time.LocalDate;
import java.time.LocalTime;
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
        return "/events/showEvent";
    }

    @GetMapping("events/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model){
        model.addAttribute("editedEvent",eventsDao.getById(id));
        return "events/edit";
    }

    @PostMapping("events/edit/{id}")
    public String editEvent(@PathVariable Long id, @RequestParam String name, @RequestParam LocalDate date, @RequestParam LocalTime time, @RequestParam String location, @RequestParam Trail trail, @RequestParam String meetLocation, @RequestParam LocalTime meetTime, @RequestParam String eventDetails, @RequestParam List<PictureURL> images){
        Event event1 = new Event(name, date, time, location, trail, meetLocation, meetTime, eventDetails, images);
        eventsDao.save(event1);
        return "redirect:/events/showEvent" + id;
    }

}
