package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.models.MapPoint;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MapController {
    private MapPointsRepository mapPointsDao;

    public MapController(MapPointsRepository mapPointsDao) {
        this.mapPointsDao = mapPointsDao;
    }

    @GetMapping("/map/{trailId}")
    @ResponseBody
    public List<MapPoint> getMapPoints(@PathVariable long trailId){

        return mapPointsDao.findByTrailId(trailId);


//        AjaxResponseBody result = new AjaxResponseBody();

//        if (errors.hasErrors()) {
//
//            result.setMsg(errors.getAllErrors()
//                    .stream()
//                    .map(x -> x.getDefaultMessage())
//                    .collect(Collectors.joining(",")));
//
//            return ResponseEntity.badRequest().body(result);
//
//        }

//        if (coordinates != null) {

//            return coordinates;
//        } else {
//            result.setMsg("No coordinates found.");
//            return ResponseEntity.badRequest().body(result);
//        }
    }
}
