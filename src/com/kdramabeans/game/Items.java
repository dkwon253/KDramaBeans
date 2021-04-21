package com.kdramabeans.game;

import java.util.HashMap;

public class Items {
    private HashMap<String, String> itemDesc = new HashMap<>();

    public Items() {
        getItemDesc().put("Money", "This is money");
        getItemDesc().put("Evidence", "This is evidence of fabricated trail data");
    }

    public HashMap<String, String> getItemDesc() {
        return itemDesc;
    }
}
