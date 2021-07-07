package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.daos.PictureURLsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TrailController{
    private TrailsRepository trailsDao;
    private PictureURLsRepository pictureURLSDao;
    private MapPointsRepository mapPointsDao;

    public TrailController(TrailsRepository trailsDao, PictureURLsRepository pictureURLSDao, MapPointsRepository mapPointsDao) {
        this.trailsDao = trailsDao;
        this.pictureURLSDao = pictureURLSDao;
        this.mapPointsDao = mapPointsDao;
    }

    // showTrail.html- shows individual trail with all trail details
    @GetMapping("/trails/{id}")
    public String individualTrailPage(@PathVariable Long id, Model model){
        Trail trail = trailsDao.getById(id);
        List<MapPoint> coordinates = mapPointsDao.findByTrailId(id);
        model.addAttribute("trailId", id);
        model.addAttribute("trail", trail);

        return "trails/showTrail";
    }

    @PostMapping("/trails/create")
    @ResponseBody
    public Trail createTrail(@Valid @RequestBody Trail trail) {
        System.out.println("trail.getTrailImages() = " + trail.getTrailImages());

        Trail trailSaved = trailsDao.save(trail);
        for (PictureURL url : trail.getTrailImages()) {
            url.setTrail(trailSaved);
            pictureURLSDao.save(url);
        }
        return trailSaved;
    }
}

