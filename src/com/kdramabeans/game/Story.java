package com.kdramabeans.game;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Story {

    /*
        fields
     */
    private JSONObject data;
    private JSONObject scene;
    private Map<String, Map> options = new HashMap<>();
    private String currentOption;
    private List<Item> sceneItems = new ArrayList<>();
    private List<String> hiddenItems = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private JSONObject randomEvent = new RandomEvents().getEvent();
    private boolean isRestart = false;
    private boolean eventTrigger = false;
    private boolean isAtEnd = false;


    /*ctor
      gets story information from a .json file, makes it into a JSON object, and then saves the current scene
      to be "intro" and saves the items that are in the scene into a List called "sceneItems"
     */
    public Story() throws Exception {
        InputStreamReader file = new InputStreamReader(this.getClass().getResourceAsStream("story.json"),
                StandardCharsets.UTF_8);
        Object obj = new JSONParser().parse(file);
        JSONObject jsonObj = (JSONObject) obj;
        this.data = jsonObj;
        //set "intro" as starting scene
        this.scene = (JSONObject) jsonObj.get("intro");
        setSceneItems();
    }

    /*
        methods/functions
     */

    //brings user to the next scene by resetting the scene, the items in the scene, and clearing their options
    public void nextScene(boolean isGUI) {
        setScene(isGUI);
        resetOptions();
        sceneItems.clear();
        setSceneItems();
        hiddenItems.clear();
        setHiddenItems();
    }

    // sets the scene, it will check if the scene ends the game or not and display the description
    private void setScene(boolean isGUI) {
        JSONObject newOption = (JSONObject) options.get(currentOption);
        String nextScene = (String) newOption.get("nextScene");
        JSONObject currentScene = (JSONObject) data.get(nextScene);
        if ((boolean) currentScene.get("ending")) {
            isAtEnd = true;
            if (isGUI) {
                runGUIEnding(currentScene);
            } else {
                runEndingScene(currentScene);
            }
        } else if (eventTrigger) {
            randomOrNextScene(currentScene);
        } else {
            this.scene = currentScene;
        }
    }

    private void runGUIEnding(JSONObject currentScene) {
        String ending = "\nThis is the end of the game, if you want to start again, click the restart button.";
        currentScene.put("description", currentScene.get("description") + ending);
        this.scene = currentScene;
    }

    //if the user has hit a "dead end" they have the option to restart the game
    private void runEndingScene(JSONObject currentScene) {
        String msg = (String) currentScene.get("description");
        System.out.println(msg);
        System.out.println("Do you want to play again? ");
        boolean isRightResponse = false;
        while (!isRightResponse) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("yes")) {
                isRightResponse = true;
                isRestart = true;
                isAtEnd = false;
                restartGame();
            } else if (input.equals("no")) {
                System.exit(0);
            }
        }
    }

    //this will set either a "random" scene or user's choice of scene
    private void randomOrNextScene(JSONObject currentScene) {
        Random rand = new Random();
        int n = rand.nextInt(10);
        if (n <= 2) {
            this.scene = randomEvent;
        } else {
            this.scene = currentScene;
        }
    }

    // restarts the game - resets scene back to intro and clears all options and items
    public void restartGame() {
        this.scene = (JSONObject) data.get("intro");
        sceneItems.clear();
        setSceneItems();
        hiddenItems.clear();
        setHiddenItems();
        resetOptions();
    }

    // pulls from items.json and sets the options the player can choose
    public void setOptions(String item) {
        String key = Integer.toString(options.size() + 1);
        Item itemObj = sceneItems.stream().filter(obj -> obj.getName().equalsIgnoreCase(item)).findAny().orElse(null);
        if (itemObj.getOption().get("description") != null) {
            options.put(key, itemObj.getOption());
        }
        sceneItems.remove(itemObj);
    }

    // pulls from items list in story.json and displays choices to the player
    private void setSceneItems() {

        List items = (List) scene.get("items");
        items.forEach(item -> {
            try {
                sceneItems.add(new Item(item.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void setHiddenItems() {

        List items = (List) scene.get("hidden");
        items.forEach(item -> {
            try {
                hiddenItems.add(item.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    //check if current scene has the item by comparing to the name of each item
    public boolean hasItem(String itemName) {
        for (Item sceneItem : sceneItems) {
            if (sceneItem.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    //check if current scene has hidden item
    public boolean hasHidden(String itemName) {
        for (String hiddenItem : hiddenItems) {
            if (hiddenItem.equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    // prints items for the currentScene
    public String printItems() {
        String result = "";
        if (!getEnding()) {
            result += "\nHere are the items you see: ";
            for (Item sceneItem : sceneItems) {
                result += ("\n" + sceneItem.getName());
            }
        }
        return result;
    }

    // prints the story description
    public String printStory() {
        return "SCENE: \n" + getDescription() + "\n";
    }

    // iterates over story.json and depending on the item picked, will give you certain options
    public String printOptions() {
        String result = "";
        if (options.size() > 0) {
            Iterator<Map.Entry<String, Map>> itr1 = options.entrySet().iterator();
            result += "\nHere are your options: ";
            while (itr1.hasNext()) {
                Map.Entry<String, Map> pair = itr1.next();
                JSONObject msg = (JSONObject) pair.getValue();
                result += ("\n" + pair.getKey() + " : " + msg.get("description"));
            }
        }
        return result;
    }

    /*
     getters and setters for private fields
     */
    public void resetOptions() {
        options.clear();
    }

    public void setCurrentOption(String option) {
        this.currentOption = option;
    }

    public Map<String, Map> getOptions() {
        return options;
    }

    public String getDescription() {
        return (String) scene.get("description");
    }

    public boolean getEnding() {
        return (boolean) scene.get("ending");
    }

    public boolean isRestart() {
        return isRestart;
    }

    public void setRestart(boolean restart) {
        isRestart = restart;
    }


    public void setEventTrigger(boolean eventTrigger) {
        this.eventTrigger = eventTrigger;
    }

    public boolean isAtEnd() {
        return isAtEnd;
    }
}
