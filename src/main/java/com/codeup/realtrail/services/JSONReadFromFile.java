package com.codeup.realtrail.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataInput;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONReadFromFile {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("/Users/shan/IdeaProjects/realtrail/data/SA-path.geojson"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray pathList = (JSONArray) jsonObject.get("features");

            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
            int i = 0;
            for (JSONObject object : (Iterable<JSONObject>) pathList) {
                System.out.println(object);
                JSONObject properties = (JSONObject) jsonObject.get("properties");
                Map<String,String> result =
                        new ObjectMapper().readValue((DataInput) properties, HashMap.class);
                if(result.get("name") != null) {
                    i++;
                    System.out.println(object);
                }
            }
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
