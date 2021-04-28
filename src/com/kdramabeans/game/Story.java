package com.kdramabeans.game;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Story {

    private JSONObject data;
    private JSONObject scene;
    private Map<String, Map> options = new HashMap<>();
    private String currentOption;
    private List<Item> sceneItems = new ArrayList<Item>();
    private Scanner scanner = new Scanner(System.in);
    private JSONObject randomEvent = new RandomEvents().getEvent();
    boolean isRestart = false;

    public boolean isRestart() {
        return isRestart;
    }

    public void setRestart(boolean restart) {
        isRestart = restart;
    }

    public Story() throws Exception {
//        Object obj = new JSONParser().parse(new FileReader("../KDramaBeans/src/story.json"));
        InputStreamReader file = new InputStreamReader(this.getClass().getResourceAsStream("story.json"),
                StandardCharsets.UTF_8);
        Object obj = new JSONParser().parse(file);
        JSONObject jsonObj = (JSONObject) obj;
        this.data = jsonObj;
        this.scene = (JSONObject) jsonObj.get("intro");
        setSceneItems();
    }

    public void nextScene() {
        setScene();
        resetOptions();
        sceneItems.clear();
        setSceneItems();
    }

    public void setScene() {
        JSONObject newOption = (JSONObject) options.get(currentOption);
        String nextScene = (String) newOption.get("nextScene");
        JSONObject currentScene = (JSONObject) data.get(nextScene);
        if ((boolean) currentScene.get("ending")) {
            String msg = (String) currentScene.get("description");
            System.out.println(msg);
            System.out.println("Do you want to play again? ");
            boolean isRightResponse = false;
            while (!isRightResponse) {
                String input = scanner.nextLine().toLowerCase();
                if (input.equals("yes")) {
                    isRightResponse = true;
                    isRestart = true;
                    restartGame();
                } else if (input.equals("no")) {
                    System.exit(0);
                }
            }
        } else {
            Random rand = new Random();
            int n = rand.nextInt(10);
            if (n <= 2) {
                this.scene = randomEvent;
            } else {
                this.scene = currentScene;
            }
        }
    }

    public void restartGame() {
        this.scene = (JSONObject) data.get("intro");
        sceneItems.clear();
        setSceneItems();
        resetOptions();
    }

    public Map<String, Map> getOptions() {
        return options;
    }

    public void setOptions(String item) {
        String key = Integer.toString(options.size() + 1);
        Item itemObj = sceneItems.stream().filter(obj -> obj.getName().equalsIgnoreCase(item)).findAny().orElse(null);
        if (itemObj.getOption().get("description") != null) {
            options.put(key, itemObj.getOption());
        }
        sceneItems.remove(itemObj);
    }

    public void resetOptions() {
        options.clear();
    }

    public void setCurrentOption(String option) {
        this.currentOption = option;
    }

    public String getDescription() {
        return (String) scene.get("description");
    }

    public boolean getEnding() {
        return (boolean) scene.get("ending");
    }

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

    public boolean hasItem(String itemName) {
        boolean result = false;
        for (int index = 0; index < sceneItems.size(); index++) {
            if (sceneItems.get(index).getName().equalsIgnoreCase(itemName)) {
                result = true;
            }
        }
        return result;
    }

    public void printItems() {
        if (!getEnding()) {
            System.out.println("Here are the items you see: ");
            sceneItems.forEach(item -> System.out.println(item.getName()));
        }
    }

//    public void printStory() {
//        System.out.println("These are your commands:\n" +
//                "EXAMINE + USE + GRAB + CHOOSE\n");
//        System.out.println(getDescription());
//        if (!getEnding()) {
//            System.out.println("Here are the items you see: ");
//            printItems();
//        }
//    }

    public void printStory() {
        System.out.println(getDescription());
    }

    public void printOptions() {
        if (options.size() > 0) {
            Iterator<Map.Entry<String, Map>> itr1 = options.entrySet().iterator();
            System.out.println("\nHere are your options: ");
            while (itr1.hasNext()) {
                Map.Entry<String, Map> pair = itr1.next();
                JSONObject msg = (JSONObject) pair.getValue();
                System.out.println(pair.getKey() + " : " + msg.get("description"));
            }
        }
    }
}
