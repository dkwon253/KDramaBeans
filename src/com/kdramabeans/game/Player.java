package com.kdramabeans.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    /*
      FIELDS
     */
    private List<String> grabbedItems = new ArrayList<>(); //player's inventory

    /*
      METHODS/FUNCTIONS
     */
    //adds an item to the Player's inventory
    public void grabItem(String item) {
        System.out.println("You have grabbed: " + item);
        grabbedItems.add(item);
    }

    // dynamically prints items in your inventory when player chooses to grab from the scene
    public void printGrabbedItems() {
        System.out.println("Items in your inventory: " + grabbedItems);
    }

    //check if inventory contains the grabbed item
    public boolean hasGrabbedItem(String item) {
        return grabbedItems.contains(item);
    }

    //clears items in the inventory
    public void clearItems() {
        grabbedItems.clear();
    }

    /*
      GETTER
     */
    public List<String> getGrabbedItems() {
        return grabbedItems;
    }
}
