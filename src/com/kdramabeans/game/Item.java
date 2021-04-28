package com.kdramabeans.game;

import java.util.*;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Item {
    private JSONObject data;
    private String name;
    private String description;
    private Map option;

    public Item() throws Exception {
        Object obj = new JSONParser().parse(new FileReader("../KDramaBeans/src/items.json"));
        JSONObject jsonObj = (JSONObject) obj;
        this.data = jsonObj;
    }

    public Item(String item) throws Exception {
        Object obj = new JSONParser().parse(new FileReader("../KDramaBeans/src/items.json"));
        JSONObject jsonObj = (JSONObject) obj;
        JSONObject itemObj = (JSONObject) jsonObj.get(item);
        this.data = jsonObj;
        this.name = item;
        this.description = (String) itemObj.get("description");
        setOption(itemObj);
    }

    public String getName() {
        return name;
    }

    public void getItemDescription(String itemName) {
        JSONObject itemObj = (JSONObject) data.get(itemName);
        String itemD = (String) itemObj.get("description");
        System.out.println(itemName + ": " + itemD);
    }

    public Map getOption() {
        return option;
    }

    private void setOption(JSONObject itemObj) {
        this.option = ((Map) itemObj.get("option"));
    }

    public boolean contains(String item) {
        return data.containsKey(item);
    }
}
