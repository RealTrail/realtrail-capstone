package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.*;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.models.*;
import com.codeup.realtrail.services.EmailService;
import com.codeup.realtrail.services.UserService;
import com.codeup.realtrail.daos.EventCommentsRepository;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class EventController {
    private EventsRepository eventsDao;
    private TrailsRepository trailsDao;
    private MapPointsRepository mapPointsDao;
    private final EmailService emailService;
    private UserService userService;
    private EventParticipantsRepository participantsDao;
    private EventCommentsRepository eventCommentsDao;

    //Importing File Stack Api Key
    @Value("${filestack.api.key}")
    private String filestackApi;

    // importing mapbox token
    @Value("pk.eyJ1Ijoia2FjaGlrYWNoaWN1aSIsImEiOiJja25hanJ6ZnMwcHpnMnZtbDZ1MGh5dms1In0.JAsEFoNV2QP1XXVWXlfQxA")
    private String mapboxToken;

    public EventController(EventsRepository eventsDao, TrailsRepository trailsDao, MapPointsRepository mapPointsDao, EmailService emailService, UserService userService, EventParticipantsRepository participantsDao, EventCommentsRepository eventCommentsDao) {
        this.eventsDao = eventsDao;
        this.trailsDao = trailsDao;
        this.mapPointsDao = mapPointsDao;
        this.emailService = emailService;
        this.userService = userService;
        this.participantsDao = participantsDao;
        this.eventCommentsDao = eventCommentsDao;
    }

    // Create Event
    @GetMapping("/create")
    public String showCreateEventPage(Model model, Principal principal) {
        if (principal != null) {
            // get all the trails and mapPoints
            List<Trail> trailList = trailsDao.findAll();
            List<MapPoint> mapPointList = mapPointsDao.findAll();
            model.addAttribute("event", new Event());
            model.addAttribute("trails", trailList);
            model.addAttribute("mapPoints", mapPointList);
            model.addAttribute("fileStackApi", filestackApi);
            model.addAttribute("mapboxToken", mapboxToken);
            return "events/createEvent";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/create")
    public String saveEvent(@ModelAttribute Event event,
                            @RequestParam(name = "eventDate") String eventDate,
                            @RequestParam(name = "eventMeetTime") String eventMeetTime,
                            @RequestParam(name = "eventTime") String eventTime,
                            @RequestParam(name = "trailOption") String trailOption,
                            @RequestParam(name = "trailOptions") String trailId,
                            @RequestParam(name = "images") String images,
                            Model model) throws ParseException {
        // connect user to new event being created
        User loggedInUser = userService.getLoggedInUser();

        // connect user to new event being created
        event.setOwner(loggedInUser);
        System.out.println(eventDate);
        System.out.println(eventMeetTime);
        System.out.println(eventTime);

        if (trailOption.equals("existing trail")) {
            // get selected trail
            Trail trail = trailsDao.findById(Long.parseLong(trailId));
            event.setTrail(trail);
        } else {

            // set the images to the event
            List<String> urls = new ArrayList<>(Arrays.asList(images.split(", ")));
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = formatter.parse(eventDate);
        LocalDate localDate = newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        event.setTime(LocalTime.parse(eventTime));
        event.setMeetTime(LocalTime.parse(eventMeetTime));

        event.setDate(localDate);
        System.out.println(newDate);

        Event saveEvent = eventsDao.save(event);
//        emailService.prepareAndSend(event,"new event created", event.getName());
        model.addAttribute("event", event);

        return "redirect:/events/" + saveEvent.getId();
    }

    @GetMapping("/events")
    public String eventsPage(Model model) {
        List<Event> eventsList = eventsDao.findAll();
        model.addAttribute("noEventsFound", eventsList.size() == 0);
        model.addAttribute("events", eventsList);
        return "events/showAllEvents";
    }

    // showEvent.html page
    @GetMapping("/events/{id}")
    public String individualEventPage(@PathVariable Long id, Model model, Principal principal) {
        User user = userService.getLoggedInUser();
        Event event = eventsDao.getById(id);
        EventComment eventComment = new EventComment();
        model.addAttribute("eventId", id);
        model.addAttribute("event", event);
        model.addAttribute("user", user);
        model.addAttribute("eventComment",eventComment);
        model.addAttribute("postUrl", "/events/" + id + "/comment");
        return "events/showEvent";
    }

    @GetMapping("/events/{id}/edit")
    public String showEditEvent(@PathVariable long id, Model model, Principal principal) {
        String errorMessage;
        if (principal != null) {
            User user = userService.getLoggedInUser();
            Event event = eventsDao.getById(id);
            if (event.getOwner().getId() == user.getId()) {
                model.addAttribute("event", event);
                return "events/editEvent";
            } else {
                errorMessage = "User is not event owner";
                model.addAttribute("errorMessage", errorMessage);
                return "redirect:/profile";
            }
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/events/{id}/edit")
    public String updateEvent(@ModelAttribute Event event, Model model) {
        eventsDao.save(event);
        return "events/showEvent";
    }


    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable long id) {
        eventsDao.deleteById(id);
        return "redirect:/profile";
    }

//    @GetMapping("/search")
//    public String searchByName(Model model, @RequestParam(name = "term") String term){
//        List<Event> events = eventsDao.searchByName(term);
//        model.addAttribute("events", events);
//        return "events/showAllEvents";
//    }

    @PostMapping("/events/{id}/join")
    public String joinEvent(@PathVariable long id) {
        Event event = eventsDao.getById(id);
        User user = userService.getLoggedInUser();
        List<User> participants = event.getParticipants();
        if (!participants.contains(user)) {
            participants.add(user);
            event.setParticipants(participants);
            eventsDao.save(event);
            emailService.prepareAndSendJoin(user, event.getName(), "Thank you for joining the event!\n" + event.getDate() + "\n" + event.getEventDetails());
            return ("redirect:/events/" + id + "?joined");
        } else {
            return "redirect:/events/" + id + "?alreadyjoined";
        }
    }

    @PostMapping("/events/{id}/comment")
    public String saveEventComment(@PathVariable long id, @ModelAttribute EventComment eventComment){
        User user = userService.getLoggedInUser();
        Event event = eventsDao.getById(id);
        LocalDateTime date = LocalDateTime.now();
        eventComment.setDate(date);
        eventComment.setEvent(event);
        eventComment.setOwner(user);
        eventCommentsDao.save(eventComment);

        return "redirect:/events/" + id;
    }
//Cancel
    @PostMapping("/events/{id}/cancel")
    public String cancelEvent (@PathVariable long id){
        participantsDao.deleteById(id);
        return "redirect:/profile";
    }
}


