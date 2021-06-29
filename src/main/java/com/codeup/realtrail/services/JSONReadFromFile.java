package com.codeup.realtrail.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataInput;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONReadFromFile {
    private static FileWriter file;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("/Users/shan/IdeaProjects/realtrail/data/SA-path.geojson"));

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
                    if (name.contains("Trail") || name.contains("trail")) {
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
                file = new FileWriter("/Users/shan/IdeaProjects/realtrail/data/SATrails.geojson");
                file.write(jsonObject.toJSONString());
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
