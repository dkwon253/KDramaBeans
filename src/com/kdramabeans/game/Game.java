package com.kdramabeans.game;

import java.io.File;
import java.util.Scanner;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

public class Game {
    /*
        fields
     */
    private Scanner scanner = new Scanner(System.in);
    private Player player = new Player();
    private Story story = new Story();
    private Item item = new Item();
    private BGM music = new BGM();
    boolean enteredQuit = false;
    boolean enteredHelp = false;
    private List<String> eventItems = Arrays.asList("wallet", "watch", "business card");
    private Map<String, String> evidenceMap = new HashMap<>(){{
        put("watch", "evidence 1");
        put("business card", "evidence 2");
        put("rose", "evidence 3");
        put("bitcoin", "evidence 4");
        put("voice recorder", "evidence 5");
    }};
    /*
        ctor
     */
    public Game() throws Exception {
    }

    /*
        methods/functions
     */

    //this method keeps the user in a loop -- will keep prompting them until they enter "quit"
    public void start() {
//        music.playSong();
        while (!enteredQuit) {
            if (enteredHelp) {
                enteredHelp = false;
            } else {
                if (story.isRestart()) {
                    player.clearItems();
                    player.clearEvidence();
                    story.setRestart(false);
                }
                System.out.println(story.printStory());
                System.out.println(player.printGrabbedItems());
                System.out.println(player.printEvidence());
                System.out.println(story.printItems());
            }
            if(hasEventItem()){
                story.setEventTrigger(true);
            }
            promptUser();
        }
        music.stopSong();
    }

    //check if the player's grabbed items has the event trigger items.
    private boolean hasEventItem() {
        return !Collections.disjoint(player.getGrabbedItems(), eventItems);
    }

    //prompts the user to enter a command and/or noun, and captures the input to determine next move
    private void promptUser() {
        if (player.getGrabbedItems().size() > 0) {
            System.out.println(story.printOptions());
        }
        try {
            String[] input = StringUtils.split(scanner.nextLine().toLowerCase().trim(), " ", 2);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            switch (input[0]) {
                case "quit":
                    System.out.println("Quitting...");
                    enteredQuit = true;
                    break;
                case "restart":
                    System.out.println("Restarting...");
                    story.restartGame();
                    player.clearItems();
                    player.clearEvidence();
                    break;
                case "help":
                    System.out.println("These are your commands:\n" +
                            "EXAMINE + GRAB + CHOOSE + QUIT + RESTART\n");
                    enteredHelp = true;
                    break;
                default:
                    System.out.println(executeCommand(input));;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: you didn't enter your move correctly");
        }

    }

//    private String guiPrompt(String textField) {
////        if (player.getGrabbedItems().size() > 0) {
////            System.out.println(story.printOptions());
////        }TODO: add printOptions in GUI
//
//        try {
//            String[] input = StringUtils.split(textField.toLowerCase().trim(), " ", 2);
//            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
//
//            switch (input[0]) {
//                case "quit":
//                    System.exit(0);
//                    break;
//                case "restart":
//                    story.restartGame();
//                    player.clearItems();
//                    return "Restarting...";
//                    break;
//                case "help":
//                    enteredHelp = true;
//                    return "These are your commands:\n" +
//                            "EXAMINE + GRAB + CHOOSE + QUIT + RESTART\n";
//                    break;
//                default:
//                    return executeCommand(input);
//            }
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return "Error: you didn't enter your move correctly";
//        }
//
//    }

    // function that reads user's input and executes based on command
    public String executeCommand(String[] input) {

        switch (input[0]) {
            case "examine":
                if (story.hasItem(input[1]) || player.hasGrabbedItem(input[1])) {
                    return item.getItemDescription(input[1]);
                } else {
                    return "You cannot examine that.\n";
                }
            case "drop":
                return player.dropItem(input[1]);
            case "grab":
                if (story.hasItem(input[1]) && !player.hasGrabbedItem(input[1])) {
                    if (player.grabItem(input[1])) {
                        story.setOptions(input[1]);
                        return "You have grabbed " + input[1];
                    }
                } else {
                    return "You cannot grab that.\n";
                }
            case "choose":
                if (story.getOptions().containsKey(input[1])) {
                    story.setCurrentOption(input[1]);
                    story.nextScene();
                    if (story.isAtEnd()) {
                        music.changeSong(new File("..KDramaBeans/songs/sad.wav").toURI().toString());
                    }
                    return "You chose option : " + input[1];
                } else {
                   return "Not an option\n";
                }
            case "use":
                String evidence = evidenceMap.get(input[1]);
                System.out.println(evidence);
                if (player.hasGrabbedItem(input[1]) && story.hasHidden(evidence)){
                    player.addEvidence(evidence);
                    return "You have used : " + input[1] + ", and you collected : " + evidence;
                } else{
                    return "You don't have this item in your inventory or your item does not work here";
                }
            default:
                return "Not a command\n";
        }
    }
}
