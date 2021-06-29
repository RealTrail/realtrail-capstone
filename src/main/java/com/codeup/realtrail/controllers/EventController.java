package com.codeup.realtrail.controllers;

import com.codeup.realtrail.models.User;
import com.codeup.realtrail.daos.EventsRepository;
import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.*;
import com.codeup.realtrail.services.EmailService;
import com.codeup.realtrail.services.StringService;
import com.codeup.realtrail.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class EventController {
    private EventsRepository eventsDao;
    private UsersRepository usersDao;
    private final StringService stringService;
    private final EmailService emailService;
    private UserService userService;

    //Importing File Stack Api Key
    @Value("${filestack.api.key}")
    private String  filestackApi;

    public EventController(EventsRepository eventsDao, UsersRepository usersDao, StringService stringService, EmailService emailService, UserService userService) {
        this.eventsDao = eventsDao;
        this.usersDao = usersDao;
        this.stringService = stringService;
        this.emailService = emailService;
        this.userService = userService;
    }

    // Create Event
    @GetMapping("/create")
    public String showCreateEventPage(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.getLoggedInUser();
            model.addAttribute("event", new Event());
            model.addAttribute("filestackapi", filestackApi);
            return "events/createEvent";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/create")
    public String saveCreatedEvent(@ModelAttribute Event event, Model model, @RequestParam (name = "eventDate") String eventDate, @RequestParam(name = "eventMeetTime") String eventMeetTime, @RequestParam (name = "eventTime") String eventTime) throws ParseException {
        // connect user to new event being created
        User loggedInUser = userService.getLoggedInUser();

        event.setOwner(loggedInUser);
        System.out.println(eventDate);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = formatter.parse(eventDate);
        LocalDate localDate = newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        event.setTime(LocalTime.parse(eventTime));
        event.setMeetTime(LocalTime.parse(eventMeetTime));

        event.setDate(localDate);
        System.out.println(newDate);

        Event saveEvent = eventsDao.save(event);
        emailService.prepareAndSend(event,"new event created", event.getName());
        model.addAttribute("event", event);

        return "redirect:/events/" + saveEvent.getId();
    }


    @GetMapping("/events")
    public String eventsPage(Model model){
        List<Event> eventsList = eventsDao.findAll();
        model.addAttribute("noEventsFound", eventsList.size() == 0);
        model.addAttribute("events", eventsList);
        return "events/showAllEvents";
    }

    // show.html page
    @GetMapping("/events/{id}")
    public String individualEventPage(@PathVariable Long id, Model model){
        Event event= eventsDao.getById(id);
        model.addAttribute("eventId", id);
        model.addAttribute("event", event);

        return "events/showEvent";
    }

    @GetMapping("/events/{id}/edit")
    public String showEditEvent(Model model, @PathVariable Long id){
        Event eventToEdit = eventsDao.getById(id);
        model.addAttribute("event", eventToEdit);
        return "events/editEvent";
    }

    @PostMapping("/events/{id}/edit")
    @ResponseBody
    public String updateEvent(@PathVariable Long id,
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "date") LocalDate date,
                              @RequestParam(name = "time") LocalTime time,
                              @RequestParam(name = "location") String location,
                              @RequestParam(name = "trail") Trail trail,
                              @RequestParam(name = "meetLocation") String meetLocation,
                              @RequestParam(name = "meetTime") LocalTime meetTime,
                              @RequestParam(name = "eventDetails") String eventDetails,
                              @RequestParam(name = "images") List<PictureURL> images)
    {
        // find the event
        Event foundEvent = eventsDao.getById(id);
        // edit the event
        foundEvent.setName(name);
        foundEvent.setDate(date);
        foundEvent.setTime(time);
        foundEvent.setLocation(location);
        foundEvent.setTrail(trail);
        foundEvent.setMeetLocation(meetLocation);
        foundEvent.setMeetTime(meetTime);
        foundEvent.setEventDetails(eventDetails);
        foundEvent.setImages(images);
        // save the changes
        eventsDao.save(foundEvent);
        return "events/editEvent";
    }

    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable long id){
        eventsDao.deleteById(id);
        return "redirect:/events/showAllEvents";
    }

    @GetMapping("/search")
    public String searchByName(Model model, @RequestParam(name = "term") String term){
        List<Event> events = eventsDao.searchByName(term);
        model.addAttribute("events", events);
        return "events/showAllEvents";
    }

}
