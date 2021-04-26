package com.kdramabeans.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {
    private List<String> grabbedItems = new ArrayList<>();

    public Player(){

    }

    public void grabItem(String item){
        System.out.println("You have grabbed: " + item);
        grabbedItems.add(item);
    }

    public List<String> getGrabbedItems() {
        return grabbedItems;
    }

    public boolean hasGrabbedItem(String item){
        return grabbedItems.contains(item);
    }


}
