//package com.codeup.realtrail.controllers;
//
//import com.codeup.realtrail.daos.MapPointsRepository;
//import com.codeup.realtrail.daos.TrailsRepository;
//import com.codeup.realtrail.models.MapPoints;
//import com.codeup.realtrail.models.Trail;
//import com.mysql.cj.jdbc.Driver;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.ui.Model;
//
//import javax.servlet.jsp.jstl.core.Config;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MapPointsController {
//    private TrailsRepository trailsDao;
//    private Connection connection;
//
////    public MapPointsJsonToDB(TrailsRepository trailsDao, Config config) {
////        this.trailsDao = trailsDao;
////        try {
////            DriverManager.registerDriver(new Driver());
////            connection = DriverManager.getConnection(
////                    config.getUrl(),
////                    config.getUser(),
////                    config.getPassword()
////            );
////        } catch (SQLException e) {
////            throw new RuntimeException("Error connecting to the database!", e);
////        }
////    }
//
//    public static Connection ConnectToDB() throws Exception {
//        //Registering the Driver
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        //Getting the connection
//        String mysqlUrl = "jdbc:mysql://localhost/realtrail_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//        Connection connection = DriverManager.getConnection(mysqlUrl, "realtrail", "realtrail@123");
//        System.out.println("Connection established......");
//        return connection;
//    }
//
//    public List<String> getAllTrailNames() {
//        // Get all the trails
//        List<Trail> trails = trailsDao.findAll();
//        List<String> trailNames = new ArrayList<>();
//        for (Trail trail : trails)  {
//            trailNames.add(trail.getName());
//        }
//        return trailNames;
//    }
//
//    public static void convertJsonToDB() {
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/government-canyon-state-natural-area.geojson";
////        List<String> trailNames = getAllTrailNames();
//
//        // Create a JSONParser object
//        JSONParser parser = new JSONParser();
//
//        try {
//            // Parse the contents of the JSON file
//            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));
//
//            // get the features list
//            JSONArray pathList = (JSONArray) jsonObject.get("features");
//
//            // iterate through the pathList which is features array in the json file
//            for (JSONObject path : (Iterable<JSONObject>) pathList) {
//                JSONObject properties = (JSONObject) path.get("properties");
//                String trailName = (String) properties.get("name");
//
//                // iterate through the trailNames
////                for (String trail : trailNames) {
//                    if(trailName != null && trailName.equalsIgnoreCase(trail)) {
//                        System.out.println("path = " + path);
//                        JSONObject mapPoints = (JSONObject) path.get("geometry");
//                        JSONArray coordinates = (JSONArray) mapPoints.get("coordinates");
//                        for (Object obj : coordinates) {
//                            String mapPointsString = obj.toString().substring(1, obj.toString().length() - 1);
//                            double lng = Double.parseDouble(mapPointsString.substring(0, mapPointsString.indexOf(",")));
//                            double lat = Double.parseDouble(mapPointsString.substring(mapPointsString.indexOf(",") + 1));
//                            System.out.println("lng = " + lng);
//                            System.out.println("lat = " + lat);
////                            Trail trailToSave = trailsDao.findTrailByName(trail);
//                            MapPoints coordinatesToSave = new MapPoints(lng, lat, trailToSave);
//                            mapPointsDao.save(coordinatesToSave);
//                        }
//                    }
////                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
////# random endpoint: ("/saveto/db") the only thing we use the response mapping for is to fire off the method to save json to db?
//
//    public static void main(String[] args) throws Exception {
//        Connection connection = ConnectToDB();
//        convertJsonToDB("/Users/shan/IdeaProjects/realtrail/data/government-canyon-state-natural-area.geojson");
//    }
//}


package com.codeup.realtrail.controllers;

        import com.codeup.realtrail.daos.MapPointsRepository;
        import com.codeup.realtrail.daos.TrailsRepository;
        import com.codeup.realtrail.models.MapPoints;
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
public class MapPointsController {
    private MapPointsRepository mapPointsDao;
    private TrailsRepository trailsDao;

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

    @GetMapping("/data/save")
    @ResponseBody
    public String convertJsonToDB() {
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/government-canyon-state-natural-area.geojson";
//        String fileName = "/Users/shan/IdeaProjects/realtrail/data/crownridge-park.geojson";
        String fileName = "/Users/shan/IdeaProjects/realtrail/data/cycle.geojson";
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
                    if(trailName != null && trailName.equalsIgnoreCase(trail)) {
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
        return "done";
    }

//    public static void main(String[] args) {
//        final MapPointsController mapPointsController = new MapPointsController();
//        mapPointsController.convertJsonToDB("/Users/shan/IdeaProjects/realtrail/data/government-canyon-state-natural-area.geojson");
//    }
}
