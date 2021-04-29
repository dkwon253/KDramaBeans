package com.kdramabeans.game;

import java.util.Scanner;
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

    /*
        ctor
     */
    public Game() throws Exception {}

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
                if (story.isRestart()){
                    player.clearItems();
                    story.setRestart(false);
                }
                story.printStory();
                player.printGrabbedItems();
                story.printItems();
            }
            promptUser();
        }
        music.stopSong();
    }

    //prompts the user to enter a command and/or noun, and captures the input to determine next move
    private void promptUser() {
        if (player.getGrabbedItems().size() > 0) {
            story.printOptions();
        }

        try {
            String[] input = StringUtils.split(scanner.nextLine().toLowerCase().trim(), " ", 2);
            switch(input[0]){
                case "quit":
                    System.out.println("Quitting..");
                    enteredQuit = true;
                    break;
                case "restart":
                    System.out.println("Restarting..");
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
                    System.out.println("You cannot examine that.");
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
                    System.out.println("You cannot grab that.");
                }
                break;
            case "choose":
                if (story.getOptions().containsKey(input[1])) {
                    story.setCurrentOption(input[1]);
                    story.nextScene();
                } else {
                    System.out.println("Not an option");
                }
                break;
            default:
                System.out.println("Not a command");
        }
    }

}
