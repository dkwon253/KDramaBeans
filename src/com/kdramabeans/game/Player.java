package com.kdramabeans.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    /*
      FIELDS
     */
    private List<String> grabbedItems = new ArrayList<>(); //player's inventory
    private int max_inventory = 3; // default is 3

    /*
     CTOR
     */
    // to instantiate player w default max inventory of 3
    public Player(){}
    //to instantiate player w a customized max inventory
    public Player(int max_inventory){
        this.max_inventory = max_inventory;
    }

    /*
      METHODS/FUNCTIONS
     */

    //adds an item to the Player's inventory only if they have not reached max capacity
    public boolean grabItem(String item) {
        boolean canGrab = false;
        if(grabbedItems.size() < max_inventory){
            System.out.println("You have grabbed: " + item);
            grabbedItems.add(item);
            canGrab = true;
        }
        else{
            System.out.println("You have too many items! Try dropping one if you really need to grab " + item);
        }
        return canGrab;
    }

    //deletes an item from user's inventory, if it's there
    public void dropItem(String item){
        if(hasGrabbedItem(item)){
            grabbedItems.remove(item);
            System.out.println("You have dropped: " + item);
        }else{
            System.out.println("You don't have one to drop.");
        }
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
