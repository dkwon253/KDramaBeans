package com.kdramabeans.game;

import java.util.HashMap;
import java.util.Map;

public class Story {

    public String storyLine(String keyword) {
        Map<String, String> kStory = new HashMap<>();
        kStory.put("Intro", "This story is about a man seeking revenge for the murder of his father.");
        kStory.put("Scene1", "Park Ji Woo is walking down the street to school");
        kStory.put("Scene2", "Test2 - David");
        kStory.put("Scene3", "Test3");
        kStory.put("Scene4", "Test4");
        kStory.put("Scene5", "Test5");
        kStory.put("Scene6", "Test6");
        kStory.put("Scene7", "Test7");
        kStory.put("Scene9", "Test8");
        return kStory.get(keyword);
    }

    public static void main(String[] args) {
        Story stor = new Story();
        System.out.println(stor.storyLine("Scene2"));
    }
}
