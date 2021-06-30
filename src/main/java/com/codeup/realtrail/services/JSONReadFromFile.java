package com.codeup.realtrail.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class JSONReadFromFile {
    private static FileWriter file;

    public static void filterData(String fileName, String newFileName, String searchedTrail) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileName));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // get the features list
            JSONArray pathList = (JSONArray) jsonObject.get("features");
            JSONArray trails = new JSONArray();

            // iterate through the pathList which is features array in the json file
            int i = 0;
            for (JSONObject path : (Iterable<JSONObject>) pathList) {
                JSONObject properties = (JSONObject) path.get("properties");
                String name = (String) properties.get("name");
                if(name != null) {
                    if (name.contains(searchedTrail)) {
                        trails.add(path);
                        i++;
                    }
                }
            }

            System.out.println(i);

            // put the filtered data in jsonObject
            jsonObject.put("type", "FeatureCollection");
            jsonObject.put("generator", "overpass-ide");
            jsonObject.put("copyright", "The data included in this document is from www.openstreetmap.org. The data is made available under ODbL.");
            jsonObject.put("timestamp", "2021-06-28T01:56:15Z");
            jsonObject.put("features", trails);

            try {
                // write the filtered data into a new file
                file = new FileWriter(newFileName);
                file.write(jsonObject.toJSONString());
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
//        filterData("/Users/shan/IdeaProjects/realtrail/data/SA-path.geojson", "/Users/shan/IdeaProjects/realtrail/data/SATrails.geojson", "Trail");
//        filterData("/Users/shan/IdeaProjects/realtrail/data/SA-path.geojson", "/Users/shan/IdeaProjects/realtrail/data/salado-creek-greenway-trail.geojson", "Salado Creek Greenway Trail");

        filterData("/Users/shan/IdeaProjects/realtrail/data/export.geojson", "/Users/shan/IdeaProjects/realtrail/data/SATrails2.geojson", "Trail");
    }
}
