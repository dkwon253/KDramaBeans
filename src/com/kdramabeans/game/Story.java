package com.kdramabeans.game;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Story {

    //some more comments
    private JSONObject data;
    private JSONObject scene;
    private String option = "1";

    public Story() throws Exception {
        Object obj = new JSONParser().parse(new FileReader("../KDramaBeans/src/story.json"));
        JSONObject jsonObj = (JSONObject) obj;
        this.data = jsonObj;
        this.scene = (JSONObject) jsonObj.get("intro");
    }

    public void nextScene() {
        JSONObject options = getOptions();
        JSONObject newOption = (JSONObject) options.get(option);
        String nextScene = (String) newOption.get("nextScene");
        this.scene = (JSONObject) data.get(nextScene);
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getDescription() {
        return (String) scene.get("description");
    }

    public JSONObject getOptions() {
        return (JSONObject) scene.get("option");
    }

    public boolean getEnding() {
        return (boolean) scene.get("ending");
    }

    public String getItems() {
        JSONObject options = getOptions();
        JSONObject newOption = (JSONObject) options.get(option);
        return (String) newOption.get("items");
    }

    public String getItemDescription() {
        JSONObject options = getOptions();
        JSONObject newOption = (JSONObject) options.get(option);
        return (String) newOption.get("itemDescription");
    }

    public void printStory(){
        System.out.println(getDescription());
        System.out.println("Here are your options: " + getOptions().toString());
    }
}
