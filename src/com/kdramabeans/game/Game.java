package com.kdramabeans.game;

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
        music.playSong();
        while (!enteredQuit) {
            if (enteredHelp) {
                enteredHelp = false;
            } else {
                if (story.isRestart()) {
                    player.clearItems();
                    story.setRestart(false);
                }
                story.printStory();
                player.printGrabbedItems();
                story.printItems();
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
            story.printOptions();
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
                    break;
                case "help":
                    System.out.println("These are your commands:\n" +
                            "EXAMINE + GRAB + CHOOSE + QUIT + RESTART\n");
                    enteredHelp = true;
                    break;
                default:
                    executeCommand(input);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: you didn't enter your move correctly");
        }

    }

    // function that reads user's input and executes based on command
    private void executeCommand(String[] input) {
        switch (input[0]) {
            case "examine":
                if (story.hasItem(input[1]) || player.hasGrabbedItem(input[1])) {
                    item.getItemDescription(input[1]);
                } else {
                    System.out.println("You cannot examine that.\n");
                }
                break;
            case "drop":
                player.dropItem(input[1]);
                break;
            case "grab":
                if (story.hasItem(input[1]) && !player.hasGrabbedItem(input[1])) {
                    if(player.grabItem(input[1])) {
                        story.setOptions(input[1]);
                    }
                } else {
                    System.out.println("You cannot grab that.\n");
                }
                break;
            case "choose":
                if (story.getOptions().containsKey(input[1])) {
                    story.setCurrentOption(input[1]);
                    story.nextScene();
                } else {
                    System.out.println("Not an option\n");
                }
                break;
            case "use":
                if (player.hasGrabbedItem(input[1])){
                    //TODO: do something here
                    player.getGrabbedItems().remove(input[1]);
                    System.out.println("You have used : " + input[1]);

                } else{
                    System.out.println("You don't have this item in your inventory");
                }
                break;
            default:
                System.out.println("Not a command\n");
        }
    }

}
