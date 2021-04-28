package com.kdramabeans.game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomEvents {

    /*
        fields
     */
    private JSONObject event;

    /*
        ctor
        Parse randomEvents.json file to json object. Then, assigns a random event to event field.
     */
    public RandomEvents () throws Exception {
        InputStreamReader file = new InputStreamReader(this.getClass().getResourceAsStream("randomEvents.json"),  StandardCharsets.UTF_8);
        Object obj = new JSONParser().parse(file);
        JSONObject jsonObj = (JSONObject) obj;
        Random R = new Random();
        String eventNumber = "event"+R.nextInt(jsonObj.size());
        event = (JSONObject) jsonObj.get(eventNumber);
    }

    /*
        getter
     */
    public JSONObject getEvent() {
        return event;
    }

}
