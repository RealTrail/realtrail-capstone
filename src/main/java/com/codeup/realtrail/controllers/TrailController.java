package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.MapPoints;
import com.codeup.realtrail.models.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;


@Controller
public class TrailController{
    private TrailsRepository trailsDao;
    private MapPointsRepository mapPointsDao;

    public TrailController(TrailsRepository trailsDao, MapPointsRepository mapPointsDao) {
        this.trailsDao = trailsDao;
        this.mapPointsDao = mapPointsDao;
    }

    @GetMapping("/trails/{id}")
    public String individualTrailPage(@PathVariable Long id, Model model){
        Trail trail = trailsDao.getById(id);
        List<MapPoints> coordinates = mapPointsDao.findByTrailId(id);
        model.addAttribute("trailId", id);
        model.addAttribute("trail", trail);

        return "trails/showTrail";
    }

//    @GetMapping("/trails/create")
//    public String getTrailForm(Model model, Principal principal) {
//        if (principal != null) {
//            model.addAttribute("trail", new Trail());
//            return "events/createEvent";
//        } else {
//            return "redirect:/login";
//        }
//    }

    @PostMapping("/trails/create")
    public String createTrail(@ModelAttribute Trail trail) {
        trailsDao.save(trail);
        return "events/createEvent";
    }

}

