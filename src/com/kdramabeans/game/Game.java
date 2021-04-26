package com.kdramabeans.game;

import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Game {
    Scanner scanner = new Scanner(System.in);
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
        if(player.getGrabbedItems().size() > 0){
            story.printOptions();
        }
        String[] input = StringUtils.split(scanner.nextLine().toLowerCase(), " ", 2);

        if (input[0].equalsIgnoreCase("quit")) {
            System.out.println("Quitting..");
            enteredQuit = true;
        } else {
            executeCommand(input);
        }

    }

    private void executeCommand(String[] input) {
        switch (input[0]) {
            case "examine":
                System.out.println("EXAMINE " + input[1]);
                break;
            case "use":
                System.out.println("USE " + input[1]);
                break;
            case "grab":
                System.out.println("GRAB " + input[1]);
                if(story.hasItem(input[1])){
                    player.grabItem(input[1]);
                    story.setOptions(input[1]);
                }
                break;
            case "choose":
                //System.out.println("CHOOSE " + input[1]);
                if(story.getOptions().containsKey(input[1])) {
                    story.setCurrentOption(input[1]);
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
