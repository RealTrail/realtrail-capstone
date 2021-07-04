package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.daos.PictureURLsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    @GetMapping("/trails/{id}")
    public String individualTrailPage(@PathVariable Long id, Model model){
        Trail trail = trailsDao.getById(id);
        List<MapPoints> coordinates = mapPointsDao.findByTrailId(id);
        model.addAttribute("trailId", id);
        model.addAttribute("trail", trail);

        return "trails/showTrail";
    }

    @PostMapping("/trails/create")
    public String createTrail(@RequestParam(name = "trailName") String trailName,
                              @RequestParam(name = "trailLength") float trailLength,
                              @RequestParam(name = "difficultyLevel") String difficultyLevel,
                              @RequestParam(name = "trailType") String trailType,
                              @RequestParam(name = "trailDetails") String trailDetails,
                              @RequestParam(name = "images") String images) {
        Trail trail = new Trail(trailName, difficultyLevel, trailLength, trailType, trailDetails);
        Trail trailSaved = trailsDao.save(trail);

        if (!images.isEmpty()) {
            // convert the urls inside hidden input into an array of url
            String[] urls = images.split(", ");
            List<PictureURL> pictureURLList = new ArrayList<>();

            for (String url : urls) {
                PictureURL pictureURL = new PictureURL(url, trailSaved);
                pictureURLList.add(pictureURL);
                pictureURLSDao.save(pictureURL);
            }

            trailSaved.setTrailImages(pictureURLList);
        }

        return "events/createEvent";
    }

}

