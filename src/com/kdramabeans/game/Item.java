package com.kdramabeans.game;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Item {
    /*
      FIELDS
     */
    private JSONObject data;
    private String name;
    private String description;
    private Map option;

    /*
      CTOR - reads in items.json file and converts to JSONObject to be called in the Game class
     */

    public Item() throws Exception {
        InputStreamReader file = new InputStreamReader(this.getClass().getResourceAsStream("items.json"),
                StandardCharsets.UTF_8);
        Object obj = new JSONParser().parse(file);
        JSONObject jsonObj = (JSONObject) obj;
        this.data = jsonObj;
    }

    public Item(String item) throws Exception {
        this();
        JSONObject itemObj = (JSONObject) data.get(item);
        this.name = item;
        this.description = (String) itemObj.get("description");
        setOption(itemObj);
    }

    /*
      METHODS/FUNCTIONS
     */

    // function to grab item description
    public void getItemDescription(String itemName) {
        JSONObject itemObj = (JSONObject) data.get(itemName);
        String itemD = (String) itemObj.get("description");
        System.out.println(StringUtils.capitalize(itemName) + ": " + itemD);
    }

    /*
      GETTERS/SETTER
     */
    public String getName() {
        return name;
    }

    public Map getOption() {
        return option;
    }

    private void setOption(JSONObject itemObj) {
        this.option = (Map) itemObj.get("option");
    }
}
