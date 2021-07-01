package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.EventsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.Trail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TrailController{
    private TrailsRepository trailsDao;
    private EventsRepository eventsDao;

    //Importing File Stack Api Key
    @Value("${filestack.api.key}")
    private String filestackApi;

    // importing mapbox token
    @Value("pk.eyJ1Ijoia2FjaGlrYWNoaWN1aSIsImEiOiJja25hanJ6ZnMwcHpnMnZtbDZ1MGh5dms1In0.JAsEFoNV2QP1XXVWXlfQxA")
    private String mapboxToken;

    public TrailController(TrailsRepository trailsDao, EventsRepository eventsDao) {
        this.trailsDao = trailsDao;
        this.eventsDao = eventsDao;
//        this.filestackApi = filestackApi;
//        this.mapboxToken = mapboxToken;
    }

    // showTrail.html- shows individual trail with all trail details
    @GetMapping("/trails/{id}")
    public String individualTrailPage(@PathVariable Long id, Model model){
        Trail trail = trailsDao.getById(id);
        model.addAttribute("trailId", id);
        model.addAttribute("trail", trail);

        return "trails/showTrail";
    }


    // showAllTrails.html- shows all trails limited trail details
    @GetMapping("/trails")
        public String trailsPage(Model model){
            List<Trail> trailsList = trailsDao.findAll();
            model.addAttribute("noTrailsFound", trailsList.size() == 0);
            model.addAttribute("trails", trailsList);
            return "trails/showAllTrails";
        }


//    // Search for a trail by category
//    @GetMapping("/search")
//        public String findTrailByName(Model model, @RequestParam(name = "")){
//            List<Trail> trails = trailsDao.searchByName();
//            model.addAttribute("trails", trails);
//            return "trails/showAllTrails";
//        }


//        // Create Event
//        @GetMapping("/create")
//        public String showCreateEventPage(Model model, Principal principal) {
//            if (principal != null) {
//                User user = userService.getLoggedInUser();
//                model.addAttribute("event", new Event());
//                model.addAttribute("filestackapi", filestackApi);
//                return "events/createEvent";
//            } else {
//                return "redirect:/login";
//            }
//        }
//
//        @PostMapping("/create")
//        public String saveCreatedEvent(@ModelAttribute Event event, Model model, @RequestParam (name = "eventDate") String eventDate, @RequestParam(name = "eventMeetTime") String eventMeetTime, @RequestParam (name = "eventTime") String eventTime) throws ParseException {
//            // connect user to new event being created
//            User loggedInUser = userService.getLoggedInUser();
//
//            event.setOwner(loggedInUser);
//            System.out.println(eventDate);
//
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date newDate = formatter.parse(eventDate);
//            LocalDate localDate = newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//            event.setTime(LocalTime.parse(eventTime));
//            event.setMeetTime(LocalTime.parse(eventMeetTime));
//
//            event.setDate(localDate);
//            System.out.println(newDate);
//
//            Event saveEvent = eventsDao.save(event);
//            emailService.prepareAndSend(event,"new event created", event.getName());
//            model.addAttribute("event", event);
//
//            return "redirect:/events/" + saveEvent.getId();
//        }
//
//
//
//

//        @GetMapping("/events/{id}/edit")
//        public String showEditEvent(Model model, @PathVariable Long id){
//            Event eventToEdit = eventsDao.getById(id);
//            model.addAttribute("event", eventToEdit);
//            return "events/editEvent";
//        }
//
//        @PostMapping("/events/{id}/delete")
//        public String deleteEvent(@PathVariable long id){
//            eventsDao.deleteById(id);
//            return "redirect:/events/showAllEvents";
//        }
//
//        @GetMapping("/search")
//        public String searchByName(Model model, @RequestParam(name = "term") String term){
//            List<Event> events = eventsDao.searchByName(term);
//            model.addAttribute("events", events);
//            return "events/showAllEvents";
//        }

//    }


}

