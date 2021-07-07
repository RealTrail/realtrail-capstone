package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.daos.PictureURLsRepository;
import com.codeup.realtrail.daos.TrailCommentsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.*;
import com.codeup.realtrail.services.UserService;
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
        List<MapPoint> coordinates = mapPointsDao.findByTrailId(id);
        TrailComment trailComment = new TrailComment();
        model.addAttribute("trailId", id);
        model.addAttribute("trail", trail);
        model.addAttribute("trailComment", trailComment);
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
}

