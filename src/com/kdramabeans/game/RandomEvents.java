package com.kdramabeans.game;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class RandomEvents {

    private JSONObject events;
    private JSONObject event;

    public RandomEvents () throws IOException, ParseException {
//        Object obj = new JSONParser().parse(new FileReader("../KDramaBeans/src/randomEvents.json"));
        InputStreamReader file = new InputStreamReader(this.getClass().getResourceAsStream("randomEvents.json"),  StandardCharsets.UTF_8);
        Object obj = new JSONParser().parse(file);
        JSONObject jsonObj = (JSONObject) obj;
        events = jsonObj;
        Random R = new Random();
        String eventNumber = "event"+R.nextInt(events.size());
        event = (JSONObject) jsonObj.get(eventNumber);
    }

    public boolean getEnding(){
        return (boolean) event.get("ending");
    }

    public String getDescription(){
        return (String) event.get("description");
    }

    public ArrayList getItems(){
        return (ArrayList) event.get("items");
    }

    public JSONObject getEvent() {
        return event;
    }

}