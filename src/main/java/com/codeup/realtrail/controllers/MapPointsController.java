package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.MapPointsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.MapPoints;
import com.codeup.realtrail.models.Trail;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapPointsController {
    private MapPointsRepository mapPointsDao;
    private TrailsRepository trailsDao;

    public MapPointsController() {
    }

    public MapPointsController(MapPointsRepository mapPointsDao, TrailsRepository trailsDao) {
        this.mapPointsDao = mapPointsDao;
        this.trailsDao = trailsDao;
    }

    public List<String> getAllTrailNames() {
        // Get all the trails
        List<Trail> trails = trailsDao.findAll();
        List<String> trailNames = new ArrayList<>();
        for (Trail trail : trails)  {
            trailNames.add(trail.getName());
        }
        return trailNames;
    }

    public void convertJsonToDB(String fileName) {
        List<String> trailNames = getAllTrailNames();

        // Create a JSONParser object
        JSONParser parser = new JSONParser();
        try {
            // Parse the contents of the JSON file
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));

            // get the features list
            JSONArray pathList = (JSONArray) jsonObject.get("features");

            // iterate through the pathList which is features array in the json file
            for (JSONObject path : (Iterable<JSONObject>) pathList) {
                JSONObject properties = (JSONObject) path.get("properties");
                String trailName = (String) properties.get("name");
                
                // iterate through the trailNames
                for (String trail : trailNames) {
                    if(trailName != null && trailName.equalsIgnoreCase(trail)) {
                        System.out.println("path = " + path);
                        JSONObject mapPoints = (JSONObject) path.get("geometry");
                        JSONArray coordinates = (JSONArray) mapPoints.get("coordinates");
                        for (Object obj : coordinates) {
                            String mapPointsString = obj.toString().substring(1, obj.toString().length() - 1);
                            double lng = Double.parseDouble(mapPointsString.substring(0, mapPointsString.indexOf(",")));
                            double lat = Double.parseDouble(mapPointsString.substring(mapPointsString.indexOf(",") + 1));
                            System.out.println("lng = " + lng);
                            System.out.println("lat = " + lat);
                            Trail trailToSave = trailsDao.findTrailByName(trail);
                            MapPoints coordinatesToSave = new MapPoints(lng, lat, trailToSave);
                            mapPointsDao.save(coordinatesToSave);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MapPointsController mapPointsController = new MapPointsController();
        mapPointsController.convertJsonToDB("/Users/shan/IdeaProjects/realtrail/data/government-canyon-state-natural-area.geojson");
    }
}
