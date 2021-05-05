package com.kdramabeans.game;


import java.util.Scanner;
import java.util.*;

public class Game {
    /*
        fields
     */
    private Scanner scanner = new Scanner(System.in);
    public Player gamePlayer;
    public Story gameStory;
    private Item item = new Item();
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
    private boolean isGUI = false;
    /*
        ctor
     */
    public Game() throws Exception {
        this.gameStory = new Story();
        this.gamePlayer = new Player();
    }

    public Game(Story guiStory, Player guiPlayer, boolean isGUI) throws Exception {
        this.gameStory = guiStory;
        this.gamePlayer = guiPlayer;
        this.isGUI = isGUI;
    }

    /*
        methods/functions
     */

    //this method keeps the user in a loop -- will keep prompting them until they enter "quit"


    //check if the player's grabbed items has the event trigger items.
    private boolean hasEventItem() {
        return !Collections.disjoint(gamePlayer.getGrabbedItems(), eventItems);
    }

    public String printOptions(){
        if (gamePlayer.getGrabbedItems().size() > 0) {
            return gameStory.printOptions();
        }
        return "";
    }
}
