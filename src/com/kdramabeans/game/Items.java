package com.kdramabeans.game;

import java.util.HashMap;

public class Items {
    private HashMap<String, String> itemDesc = new HashMap<>();

    public Items() {
        getItemDesc().put("money", "This is money");
        getItemDesc().put("evidence", "This is evidence of fabricated trail data");
    }

    public HashMap<String, String> getItemDesc() {
        return itemDesc;
    }

    public boolean contains(String item){
        if(itemDesc.containsKey(item)){
            return true;
        }else{
            return false;
        }
    }
}
