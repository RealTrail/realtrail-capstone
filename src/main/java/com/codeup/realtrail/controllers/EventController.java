package com.codeup.realtrail.controllers;


import com.codeup.realtrail.daos.EventsRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.*;
import com.codeup.realtrail.services.EmailService;
import com.codeup.realtrail.services.StringService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class EventController {
    private final StringService stringService;
    private final EmailService emailService;
    private EventsRepository eventsDao;
    private UsersRepository usersDao;

    public EventController(StringService stringService, EmailService emailService, EventsRepository eventsRepository, UsersRepository usersRepository){
        this.stringService = stringService;
        this.emailService = emailService;
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

    // Create Event
    @GetMapping("/event/create")
    public String publishEvent(@Valid Event event, Errors validation, Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("event", event);
            return "events/createEvent";
        }
        model.addAttribute("event", new Event());
        return "events/showEvent";
    }

    @PostMapping("/events/create")
    public String saveEvent(
            Event event,
            @RequestParam(name = "manager") User manager,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "date") LocalDate date,
            @RequestParam(name = "time") LocalTime time,
            @RequestParam(name = "location") String location,
            @RequestParam(name = "trail") Trail trail,
            @RequestParam(name = "meet_location") String meetLocation,
            @RequestParam(name = "meet_time") LocalTime meetTime,
            @RequestParam(name = "event-details") List<PictureURL> images,
            Model model) {
        event.setManager(manager);
        event.setName(name);
        event.setDate(date);
        event.setTime(time);
        event.setLocation(location);
        event.setTrail(trail);
        event.setMeetLocation(meetLocation);
        eventsDao.save(event);
        return "events/showEvent";
    }



    @GetMapping("events/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model){
        model.addAttribute("editedEvent",eventsDao.getById(id));
        return "events/editEvent";
    }

    @PostMapping("events/edit/{id}")
    public String editEvent(@PathVariable Long id,
                            @RequestParam String name,
                            @RequestParam LocalDate date,
                            @RequestParam LocalTime time,
                            @RequestParam String location,
                            @RequestParam Trail trail,
                            @RequestParam String meetLocation,
                            @RequestParam LocalTime meetTime,
                            @RequestParam String eventDetails,
                            @RequestParam List<PictureURL> images){
        Event event1 = new Event(name, date, time, location, trail, meetLocation, meetTime, eventDetails, images);
        eventsDao.save(event1);
        return "redirect:/events/showEvent" + id;
    }

}
