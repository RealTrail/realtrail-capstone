package com.codeup.realtrail.controllers;

        import com.codeup.realtrail.daos.MapPointsRepository;
        import com.codeup.realtrail.daos.TrailsRepository;
        import com.codeup.realtrail.models.MapPoint;
        import com.codeup.realtrail.models.Trail;
        import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;
        import org.json.simple.parser.ParseException;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ResponseBody;

        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

@Controller
public class MapPointController {
    private MapPointsRepository mapPointsDao;
    private TrailsRepository trailsDao;

    public MapPointController(MapPointsRepository mapPointsDao, TrailsRepository trailsDao) {
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

    @GetMapping("/data/save")
    @ResponseBody
    public String convertJsonToDB() {
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Joe-Johnston-Route.geojson";    // 1
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Savannah-Loop.geojson";   // 2
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Lytles-Loop.geojson";     // 3
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Wildcat-Canyon-Trail.geojson";   // 4
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Sendero-Balcones.geojson";   // 5
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Black-Hill-Loop.geojson";   // 6
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Twin-Oaks-Trail.geojson";   // 7
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Bear-Grass-Trail.geojson";   // 8
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Red-Oak-Trail.geojson";   // 9
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Apache-Creek-Greenway-Trail.geojson";   // 10
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Medina-River-Greenway-Trail.geojson";   // 11
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Salado-Creek-Greenway-Trail.geojson";   // 12
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Main-Loop.geojson";   // 13
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Restoration-Way-Trail.geojson";   // 14
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Blue-Loop.geojson";   // 15
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Baseball-Field-Trail.geojson";   // 16
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Red-Trail.geojson";   // 17
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Olmos-Basin-Loop-Trail.geojson";   // 18
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Acequia-Trail.geojson";   // 19
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/River-Walk.geojson";   // 20
        String fileName = "/Users/shan/IdeaProjects/realtrail/data/Leon-Creek-Greenway-Trail.geojson";   // 21

        List<String> trailNames = getAllTrailNames();

        // Create a JSONParser object
        JSONParser parser = new JSONParser();
        try {
            // Parse the contents of the JSON file
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));

            // get the features list
            JSONArray pathList = (JSONArray) jsonObject.get("features");

            // iterate through the trailNames
            for (String trail : trailNames) {
            // iterate through the pathList which is features array in the json file
                for (JSONObject path : (Iterable<JSONObject>) pathList) {
                    JSONObject properties = (JSONObject) path.get("properties");
                    String trailName = (String) properties.get("name");
                    if(trailName != null && trailName.contains(trail)) {
                        JSONObject mapPoints = (JSONObject) path.get("geometry");
                        JSONArray coordinates = (JSONArray) mapPoints.get("coordinates");
                        for (Object obj : coordinates) {
                            String mapPointsString = obj.toString().substring(1, obj.toString().length() - 1);
                            double lng = Double.parseDouble(mapPointsString.substring(0, mapPointsString.indexOf(",")));
                            double lat = Double.parseDouble(mapPointsString.substring(mapPointsString.indexOf(",") + 1));
                            System.out.println("lng = " + lng);
                            System.out.println("lat = " + lat);
                            Trail trailToSave = trailsDao.findTrailByName(trail);
                            MapPoint coordinatesToSave = new MapPoint(lng, lat, trailToSave);
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
        return "done";
    }

//    public static void main(String[] args) {
//        final MapPointsController mapPointsController = new MapPointsController();
//        mapPointsController.convertJsonToDB("/Users/shan/IdeaProjects/realtrail/data/government-canyon-state-natural-area.geojson");
//    }
}
