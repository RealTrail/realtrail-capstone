package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.models.MapPoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    }
}
