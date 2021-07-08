package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.daos.PictureURLsRepository;
import com.codeup.realtrail.daos.TrailCommentsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.*;
import com.codeup.realtrail.services.UserService;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class TrailController{
    private TrailsRepository trailsDao;
    private PictureURLsRepository pictureURLSDao;
    private MapPointsRepository mapPointsDao;
    private UserService userService;
    private TrailCommentsRepository trailCommentsDao;

    // importing mapbox token
    @Value("pk.eyJ1Ijoia2FjaGlrYWNoaWN1aSIsImEiOiJja25hanJ6ZnMwcHpnMnZtbDZ1MGh5dms1In0.JAsEFoNV2QP1XXVWXlfQxA")
    private String mapboxToken;

    public TrailController(TrailsRepository trailsDao, PictureURLsRepository pictureURLSDao, MapPointsRepository mapPointsDao, UserService userService, TrailCommentsRepository trailCommentsDao) {
        this.trailsDao = trailsDao;
        this.pictureURLSDao = pictureURLSDao;
        this.mapPointsDao = mapPointsDao;
        this.userService = userService;
        this.trailCommentsDao = trailCommentsDao;
    }

    // showTrail.html- shows individual trail with all trail details
    @GetMapping("/trails/{id}")
    public String individualTrailPage(@PathVariable Long id, Model model){
        Trail trail = trailsDao.getById(id);
        List<MapPoint> coordinates = mapPointsDao.findAllByTrail(trail);
        TrailComment trailComment = new TrailComment();
        model.addAttribute("trailId", id);
        model.addAttribute("trail", trail);
        model.addAttribute("trailComment", trailComment);
        model.addAttribute("coordinates", coordinates);
        model.addAttribute("mapboxToken", mapboxToken);
        model.addAttribute("postUrl", "/trails/" + id + "/comment");
        return "trails/showTrail";
    }

    @PostMapping("/trails/create")
    @ResponseBody
    public Trail createTrail(@Valid @RequestBody Trail trail) {
        System.out.println("trail.getTrailImages() = " + trail.getTrailImages());
        Trail trailSaved;
        if (trail.getTrailImages() == null) {
            trailSaved = trailsDao.save(trail);
        } else {
            trailSaved = trailsDao.save(trail);
            for (PictureURL url : trail.getTrailImages()) {
                url.setTrail(trailSaved);
                pictureURLSDao.save(url);
            }
        }
        return trailSaved;
    }

    @PostMapping("/trails/{id}/comment")
    public String saveTrailComment(@PathVariable long id, @ModelAttribute TrailComment trailComment){
        User user = userService.getLoggedInUser();
        Trail trail = trailsDao.getById(id);
        LocalDateTime date = LocalDateTime.now();
        trailComment.setDate(date);
        trailComment.setTrail(trail);
        trailComment.setOwner(user);
        trailCommentsDao.save(trailComment);
        return "redirect:/trails/" + id;
    }

    @GetMapping("/search-trail")
    public String getSearchedTrail(@RequestParam (value="keyword", required = false) String name, Model model) {
        Trail trail = trailsDao.findByKeyword("%" + name + "%");
        System.out.println("trail.getName() = " + trail.getName());
        System.out.println("trail.getId() = " + trail.getId());
        Trail searchResult = trailsDao.findById(trail.getId());
        model.addAttribute("trails", searchResult);
        return "home";
    }

    @GetMapping("/filter/difficulty-level")
    public String filterDifficultyLevel(Model model) {
        List<String> filterLevel = trailsDao.findByDifficultyLevel();
        model.addAttribute("trails", filterLevel);
        return "home";
    }


//    @GetMapping("/searchCat")
//    public
}

