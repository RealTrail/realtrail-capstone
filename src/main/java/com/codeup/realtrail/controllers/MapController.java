package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.MapPoint;
import com.codeup.realtrail.models.Trail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MapController {
    private MapPointsRepository mapPointsDao;
    private TrailsRepository trailsDao;

    public MapController(MapPointsRepository mapPointsDao, TrailsRepository trailsDao) {
        this.mapPointsDao = mapPointsDao;
        this.trailsDao = trailsDao;
    }

    @GetMapping("/trails/{trailId}/map")
    @ResponseBody
    public List<MapPoint> getMapPoints(@PathVariable long trailId){
        Trail trail = trailsDao.findById(trailId);
        return mapPointsDao.findAllByTrail(trail);
    }
}
