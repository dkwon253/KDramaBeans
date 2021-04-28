package com.kdramabeans.game;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Player player = new Player();
    Story story = new Story();
    Item item = new Item();
    boolean enteredQuit = false;
    boolean enteredHelp = false;

    public Game() throws Exception {

    }

    public void start() throws Exception {
        while (!enteredQuit) {
            if (enteredHelp) {
                enteredHelp = false;
            } else {
                story.printStory();
                player.printGrabbedItems();
                story.printItems();
            }
            promptUser();
        }
    }

    public void promptUser() {
        if (player.getGrabbedItems().size() > 0) {
            story.printOptions();
        }
        try {
            String[] input = StringUtils.split(scanner.nextLine().toLowerCase().trim(), " ", 2);
            if (input[0].equalsIgnoreCase("quit")) {
                System.out.println("Quitting..");
                enteredQuit = true;
            } else if (input[0].equalsIgnoreCase("restart")) {
                System.out.println("Restarting..");
                story.restartGame();
                player.clearItems();

            } else if (input[0].equalsIgnoreCase("help")) {
                System.out.println("These are your commands:\n" +
                        "EXAMINE + GRAB + CHOOSE + QUIT + RESTART\n");
                enteredHelp = true;
            } else {
                executeCommand(input);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: you didn't enter anything");
        }
    }

    private void executeCommand(String[] input) {
        switch (input[0]) {
            case "examine":
                if (story.hasItem(input[1]) || player.hasGrabbedItem(input[1])) {
                    item.getItemDescription(input[1]);
                } else {
                    System.out.println("You cannot examine that.");
                }
                break;
            case "grab":
                if (story.hasItem(input[1]) && !player.hasGrabbedItem(input[1])) {
                    player.grabItem(input[1]);
                    story.setOptions(input[1]);
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

    public enum COMMANDS {
        EXAMINE, USE, GET, QUIT;
    }
}
