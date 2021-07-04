package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.models.AjaxResponseBody;
import com.codeup.realtrail.models.MapPoints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RestMapController {
    private MapPointsRepository mapPointsDao;


    public RestMapController(MapPointsRepository mapPointsDao) {
        this.mapPointsDao = mapPointsDao;
    }

    @GetMapping(value = "/map/{trailId}")
    @ResponseBody
    public ResponseEntity<?> getMapPoints(@PathVariable long trailId, Errors errors){

        List<MapPoints> coordinates = mapPointsDao.findByTrailId(trailId);

        AjaxResponseBody result = new AjaxResponseBody();

        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        if (coordinates != null) {
            // Use this builder to construct a Gson instance when you need to set configuration options other than the default.
            GsonBuilder gsonBuilder = new GsonBuilder();
            // This is the main class for using Gson. Gson is typically used by first constructing a Gson instance and then invoking toJson(Object) or fromJson(String, Class) methods on it.
            // Gson instances are Thread-safe so you can reuse them freely across multiple threads.
            Gson gson = gsonBuilder.create();

            // convert coordinates list to jsonObject
            String JSONObject = gson.toJson(coordinates);

            result.setMapPointsJson(JSONObject);
            return ResponseEntity.ok(result);
        } else {
            result.setMsg("No coordinates found.");
            return ResponseEntity.badRequest().body(result);
        }
    }
}
