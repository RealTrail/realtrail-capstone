package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class TrailController{
    private TrailsRepository trailsDao;
    private MapPointsRepository mapPointsDao;

    public TrailController(TrailsRepository trailsDao, MapPointsRepository mapPointsDao) {
        this.trailsDao = trailsDao;
        this.mapPointsDao = mapPointsDao;
    }

    @GetMapping("/map/{trailId}")
    public @ResponseBody ResponseEntity<?> getMapPoints(@PathVariable Long trailId, Errors errors){
        List<MapPoints> coordinates = mapPointsDao.findByTrailId(trailId);

        AjaxResponseBody result = new AjaxResponseBody();

        if (coordinates != null) {
            // Use this builder to construct a Gson instance when you need to set configuration options other than the default.
            GsonBuilder gsonBuilder = new GsonBuilder();
            // This is the main class for using Gson. Gson is typically used by first constructing a Gson instance and then invoking toJson(Object) or fromJson(String, Class) methods on it.
            // Gson instances are Thread-safe so you can reuse them freely across multiple threads.
            Gson gson = gsonBuilder.create();

            // convert coordinates list to jsonObject
            String JSONObject = gson.toJson(coordinates);

            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = prettyGson.toJson(coordinates);

            result.setMapPointsJson(JSONObject);
            return ResponseEntity.ok(result);
        } else {
            result.setMsg("No image uploaded.");
            return ResponseEntity.badRequest().body(result);
        }
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

