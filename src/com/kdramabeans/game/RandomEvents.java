package com.kdramabeans.game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RandomEvents {

    private JSONObject events;
    private JSONObject event;

    public RandomEvents () throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("../KDramaBeans/src/randomEvents.json"));
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

    public static void main(String[] args) throws IOException, ParseException {
        RandomEvents randomEvents = new RandomEvents();
        System.out.println(randomEvents.getItems());
        System.out.printf("new screen");
    }

}