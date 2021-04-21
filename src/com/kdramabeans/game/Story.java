package com.kdramabeans.game;

import java.io.FileReader;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
                "His boss looks frantic. " +
                "What do you want to do: 1. Eavesdrop on the conversation 2. Play Candy Crush on your phone");

        kStory.put("Scene2", "One day, Park Ji Sung left work to pick you up from school. Another car crashed into your dad's car, right in the front of the school gate. You come " +
                "out of school only to find your dad is dead, and no other vehicle in sight. The only thing left besides your dad's body is his favorite watch and a picture of you and him. " +
                "Today, you became an orphan, you have no other family members. " +
                "What do you want to do: 1. Examine your dad's favorite watch 2. Continue to mourn for your father's death");

        kStory.put("Scene3", "You have been adopted by an American family and moved to New York. You attend and graduate at the top of your class from Yale University. You go on to become a successful " +
                "entrepreneur and are famous all throughout the world. Little do people know, your only goal in life is to get back at the person who murdered your father. " +
                "What do you want to do: 1. Hire a private investigator to figure out more information 2. Fly first class to Tokyo and watch the Olympics");

        kStory.put("Scene4", "You have done your research about X company, and gathered leads on how to take the company down. You fly to Korea with the alias of Daniel Park. What do you want to do: " +
                "1, meet the CEO's daughter; 2, meet with CEO to talk about an possible US investment in X company; 3, talk to X company's board members to buy their shares?");


        kStory.put("Scene5", "You meet the daughter, Lee Ji Eun. She is caught off guard by your elegance and immediately starts a conversation with you. You show you are not interested. " +
                "What do you want to do: 1. Continue to ignore her 2. Inquire more about RDX-90");

        kStory.put("Scene6", "You meet the CEO of X company, and told him your company would like to help promote the sales of RDX-90 in the United States. " +
                "What Do you want to do next: 1, request more research documentation about RDX-90. 2, Talk about Korea politics");

        kStory.put("Scene7", "You meet with a Board memeber");
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


    public static void main(String[] args) throws Exception {
        Story stor = new Story();
        Object obj = new JSONParser().parse(new FileReader("src/story.json"));
        JSONObject jo = (JSONObject) obj;

        Map intro = ((Map)jo.get("intro"));
        System.out.println(intro.get("description"));
        System.out.println(intro.get("items").toString());


//        Iterator<Map.Entry> itr1 = languages.entrySet().iterator();
//        while(itr1.hasNext()) {
//            Map.Entry pair = itr1.next();
//            System.out.println(pair.getKey() + " : " + pair.getValue());
//        }

        //System.out.println(stor.storyLine("Scene2"));
        //System.out.println("Enter your option: ");
        //stor.printMap(stor.storyLine("anything"));

    }

    private void printMap(HashMap<String, Object> map) {
        String input = scanner.nextLine();
        System.out.println(map.get(input));
        //map.forEach();
    }
}
