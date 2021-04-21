package com.kdramabeans.game;

import java.util.*;

public class Story {
    Scanner scanner = new Scanner(System.in);
    private HashMap<String, Object> intro = new HashMap<>();
    private HashMap<String, Object> scene1 = new HashMap<>();
    private HashMap<String, Object> scene2 = new HashMap<>();
    private HashMap<String, Object> scene3 = new HashMap<>();
    private HashMap<String, Object> scene4 = new HashMap<>();

    // constructor (ctor)
    public Story() {

    }

    public String storyLine(String keyword) {
        HashMap<String, String> kStory = new HashMap<>();
        // HashMap<String, HashMap<String, String>> outerMap = new HashMap<String, HashMap<String,String>>();
        // HashMap<String, String> innerMap = new HashMap<String, String>();
        // innerMap.put("InnerKey", "InnerValue");
        // outerMap.put("OuterKey", innerMap);
        Map<String, Map<String, String>> kStoryMap = new HashMap<>();
        kStory.put("Intro", "This story is about a man seeking revenge for the murder of his father. Your name is Park Ji Woo and you have become a successful entrepreneur in the states." +
                " You come back to South Korea under the alias of Daniel Park and your goal is to destroy X Company.");
        kStory.put("Scene1", "Park Ji Woo's father, Park Ji Sung is driving his boss, Lee Jin Wook to work. He is suddenly asked to pull over as his boss receives an urgent phone call. " +
                "His boss looks frantic, and caught wind of him eavesdropping. He comes back in the car and resumes driving to the office.");
        kStory.put("Scene2", "One day, Park Ji Sung left work to pick you up from school. Another car crashed into your dad's car, right in the front of the school gate. You come " +
                "out of school only to find your dad is dead, and no other vehicle in sight. Your notice on the ground, your father had left you a letter, stating if anything was to happen to him, it would be " +
                "at the hands of his boss. Today, you became an orphan, you have no other family members.");
        kStory.put("Scene3", "You have been adopted by an American family and moved to New York. You attend and graduate at the top of your class from Yale University. You go on to become a successful " +
                "entrepreneur and are famous all throughout the world. Little do people know, your only goal in life is to get back at Lee Jin Wook, who murdered your father.");
        kStory.put("Scene4", "Test4");
        kStory.put("Scene5", "Test5");
        kStory.put("Scene6", "Test6");
        kStory.put("Scene7", "Test7");
        kStory.put("Scene9", "Test8");


        HashMap<String, HashMap<String, Object>> outerMap = new HashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> innerMap = new HashMap<String, Object>();

        //items map:
//        {
//            "money": {desc}
//        }
//        if player enters "examine":
//            item = scannernextLine()
//                    sout item.get(item).get("description")


        outerMap.put("Scene1", innerMap);
        innerMap.put("Option1", "Take the money");
        innerMap.put("Option3", Arrays.toString(Arrays.asList(new String[]{"money", "bar"}).toArray()));


//        Scenario One
//        1. 	Needs to find evidence for the manipulated clinical trial data
//        2. 	International media has evidence and scandal exposed – investment lost throughout the world
//        3. 	X company stocks crash – buys half the stocks, becomes president of board of directors
//        4. 	NEED TO HAVE: Evidence, randomizer 50% board on your side, identity remains undetected
//        5. 	X company CEO goes to jail – WIN

        //return outerMap.get("Scene1");
        return kStory.get(keyword);
    }

    public HashMap<String, Object> getIntro() {
        return intro;
    }

    public HashMap<String, Object> getScene1() {
        return scene1;
    }

    public HashMap<String, Object> getScene2() {
        return scene2;
    }

    public HashMap<String, Object> getScene3() {
        return scene3;
    }

    public HashMap<String, Object> getScene4() {
        return scene4;
    }

    public static void main(String[] args) {
        Story stor = new Story();
        System.out.println(stor.storyLine("Scene2"));
        //System.out.println("Enter your option: ");
        //stor.printMap(stor.storyLine("anything"));

    }

    private void printMap(HashMap<String, Object> map) {

        String input = scanner.nextLine();
        System.out.println(map.get(input));
        //map.forEach();
    }
}
