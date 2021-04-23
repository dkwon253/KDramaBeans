package com.kdramabeans.game;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Items items = new Items();
    Player player = new Player();
    Story story = new Story();
    boolean enteredQuit = false;

    public Game() throws Exception {

    }

    public void start() throws Exception {
        while (!enteredQuit) {
            promptUser();
        }
    }

    public void promptUser() {
        story.printStory();
        String[] input = StringUtils.split(scanner.nextLine(), " ", 2);

        if (input[0].equalsIgnoreCase("quit")) {
            System.out.println("Quitting..");
            enteredQuit = true;
        } else {
            executeCommand(input);
        }

    }

    private void executeCommand(String[] input) {
        switch (input[0].toUpperCase()) {
            case "EXAMINE":
                System.out.println("EXAMINE " + input[1]);
                break;
            case "USE":
                System.out.println("USE " + input[1]);
                break;
            case "GRAB":
                System.out.println("GRAB " + input[1]);
                break;
            case "CHOOSE":
                //System.out.println("CHOOSE " + input[1]);
                if(story.getOptions().containsKey(input[1])) {
                    story.setOption(input[1]);
                    story.nextScene();
                } else {
                    System.out.println("Not an option");
                }
                break;
            default:
                System.out.println("Not a command");
        }
// will use later
//        if (items.contains(input[1].toLowerCase())) {
//            System.out.println("yay");
//        } else {
//            System.out.println("Item: " + input[1] + " is not there!");
//        }
    }

    public enum COMMANDS {
        EXAMINE, USE, GET, QUIT;
    }
}
