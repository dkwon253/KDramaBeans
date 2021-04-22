package com.kdramabeans.game;

import java.io.FileReader;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Story {

    public JSONObject getData() throws Exception {
        Object obj = new JSONParser().parse(new FileReader("src/story.json"));
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }

    public JSONObject getScene(JSONObject jsonObj, String scene) {
        return (JSONObject) jsonObj.get(scene);
    }

    public String getDescription(JSONObject jsonObj) {
        return (String) jsonObj.get("description");
    }

    public JSONObject getOptions(JSONObject jsonObj) {
        return (JSONObject) jsonObj.get("option");
    }

    public JSONArray getItems(JSONObject jsonObj) {
        return (JSONArray) jsonObj.get("items");
    }

    public JSONArray getChoices(JSONObject jsonObj) {
        return (JSONArray) jsonObj.get("choices");
    }

    public static void main(String[] args) throws Exception {
        // just to test that all the functions work
        Story story = new Story();
        JSONObject data = story.getData();
        JSONObject scene = story.getScene(data, "scene1");
        String desc = story.getDescription(scene);
        JSONObject options = story.getOptions(scene);
        JSONArray items = story.getItems(scene);
        JSONArray choices = story.getChoices(scene);
        System.out.println(desc);
        System.out.println(options);
        System.out.println(items);
        System.out.println(choices);
    }
}
