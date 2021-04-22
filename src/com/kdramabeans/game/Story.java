package com.kdramabeans.game;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Story {

    private JSONObject data;
    private JSONObject scene;

    public Story() throws Exception {
        Object obj = new JSONParser().parse(new FileReader("../KDramaBeans/src/story.json"));
        JSONObject jsonObj = (JSONObject) obj;
        this.data = jsonObj;
        this.scene = (JSONObject) jsonObj.get("intro");
    }

//    public String nextScene() {
//
//    }

    /*public void setScene() {
        if(getOptions().isEmpty()){
            this.scene =(JSONObject) data.get(getChoices().get(0));
        }else{
            System.out.println("Error");
        }
    }

    public String getDescription() {
        return (String) scene.get("description");
    }

    public JSONObject getOptions() {
        return (JSONObject) scene.get("option");
    }

    public JSONArray getItems() {
        return (JSONArray) scene.get("items");
    }

    public void printStory(){
        System.out.println(getDescription());
        System.out.println("Here are your options: " + getOptions().toString());
    }*/
}
