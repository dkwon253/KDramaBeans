package com.kdramabeans.gui;

import com.kdramabeans.game.*;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class GameFrame {
    private Player player = new Player();
    private Story story = new Story();
    private Item item = new Item();
    private Game game = new Game(story, player, true);
    private BGM music = new BGM();
    private Map<String, String> evidenceMap = new HashMap<>(){{
        put("watch", "evidence 1");
        put("business card", "evidence 2");
        put("rose", "evidence 3");
        put("bitcoin", "evidence 4");
        put("voice recorder", "evidence 5");
    }};
    private JFrame window;
    private JPanel titleNamePanel, buttonPanel, mainTextPanel, generalButtonPanel;
    private JLabel titleNameLabel, lblGif;
    private JButton startButton, nextButton, enterButton, restartButton, quitButton, helpButton, musicButton;
    private JTextArea mainTextArea, statusArea, userPrompt;
    private JTextField mainTextField;
    private Container container;
    private static final Font titleFont = new Font("Times New Roman", Font.BOLD, 30);
    private static final Font normalFont = new Font("Times New Roman", Font.PLAIN, 15);
    private TextFieldHandler textHandler = new TextFieldHandler();

    /*
      ctor that initializes the home page of the game
     */
    public GameFrame() throws Exception {
        window = new JFrame();
        titleNamePanel = new JPanel();
        buttonPanel = new JPanel();
        startButton = new JButton("Start");

        // JFrame setup
        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.WHITE);
        window.setLayout(null);
        window.setResizable(false);
        window.setTitle("KDramaBeans Game");
        container = window.getContentPane();

        // Panel Title (can be used to place all the text and scenario we want to use)
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.white);
        titleNameLabel = new JLabel("Welcome to KDramaBeans Game!");
        titleNameLabel.setForeground(Color.black);
        titleNameLabel.setFont(titleFont);

        // start button setup - should link to the start of the game
        buttonPanel.setBounds(300, 400, 200, 100);
        buttonPanel.setBackground(Color.white);
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);
        startButton.setFont(normalFont);
        startButton.addActionListener(textHandler);

        // calls up all the components and makes the screen visible
        titleNamePanel.add(titleNameLabel);
        buttonPanel.add(startButton);
        container.add(titleNamePanel);
        container.add(buttonPanel);
        window.setVisible(true);
    }

    public void createGameScreen() {
        // disables to home page panel and will display panel below
        buttonPanel.setVisible(false);

        titleNamePanel.setVisible(false);

        // sets up the panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 50, 600, 450);
        mainTextPanel.setBackground(Color.white);

        // sets up the textArea
        mainTextArea = new JTextArea(printStatus());
        mainTextArea.setBounds(100, 50, 600, 450);
        mainTextArea.setBackground(Color.white);
        mainTextArea.setForeground(Color.black);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);

        // enter button
        enterButton = new JButton("Enter");
        buttonPanel.setBounds(550, 537, 150, 50);
        enterButton.setBackground(Color.white);
        enterButton.setForeground(Color.black);
        enterButton.setFont(normalFont);
        enterButton.addActionListener(textHandler);
        buttonPanel.add(enterButton);
        generalButtons();

        // x,y,width,height

        // sets up the statusArea
        statusArea = new JTextArea();
        statusArea.setBounds(100, 350, 600, 300);
        statusArea.setBackground(Color.white);
        statusArea.setForeground(Color.black);
        statusArea.setFont(normalFont);
        statusArea.setLineWrap(true);
        statusArea.setText("These are your commands:\n" +
                "EXAMINE [Item] - to get the item description.\n" +
                "GRAB [Item] - to add item to your inventory.\n" +
                "DROP [Item] - to drop item from your inventory.\n" +
                "USE [Item] - to use item in a scene.\n" );

        //set up userPrompt label
        userPrompt = new JTextArea();
        userPrompt.setText("Type your command here:");
        userPrompt.setBounds(100, 500, 450, 25);
        userPrompt.setBackground(Color.white);
        userPrompt.setForeground(Color.black);
        userPrompt.setFont(normalFont);

        // set up textField for userInput
        mainTextField = new JTextField();
        mainTextField.setText("");
        mainTextField.setBounds(100, 525, 450, 75);
        mainTextField.setBackground(Color.white);
        mainTextField.setForeground(Color.black);
        mainTextField.setFont(normalFont);
        mainTextField.addKeyListener(textHandler);

        mainTextPanel.add(mainTextArea);
        mainTextPanel.add(statusArea);
        container.add(userPrompt);
        container.add(mainTextField);
        container.add(mainTextPanel);
        buttonPanel.setVisible(true);
    }

    public void displayGif() {
        titleNamePanel.setVisible(false);

        lblGif = new JLabel();

        try{
            Icon imgGif = new ImageIcon(getClass().getResource("resources/images/random.jpg"));
            lblGif.setIcon(imgGif);
        }catch(NullPointerException e){
            System.out.println("Can't Find Image");
        }

        lblGif.setBounds(150, 150, 455, 170);
        container.add(lblGif);

        //button
        nextButton = new JButton("Next");
        nextButton.setBackground(Color.white);
        nextButton.setForeground(Color.black);
        nextButton.setFont(normalFont);
        nextButton.addActionListener(textHandler);
        buttonPanel.add(nextButton);

    }

    public void generalButtons() {
        generalButtonPanel = new JPanel();
        quitButton = new JButton("Quit");
        restartButton = new JButton("Restart");
        helpButton = new JButton("Help");
        musicButton = new JButton("Play/Pause");

        generalButtonPanel.setBounds(100, 600, 600, 100);
        generalButtonPanel.add(musicButton);
        generalButtonPanel.add(quitButton);
        generalButtonPanel.add(helpButton);
        generalButtonPanel.add(restartButton);
        musicButton.addActionListener(textHandler);
        quitButton.addActionListener(textHandler);
        helpButton.addActionListener(textHandler);
        restartButton.addActionListener(textHandler);
        container.add(generalButtonPanel);
    }


    public class TextFieldHandler implements KeyListener, ActionListener {

        // restart, quit, help, enter(click)

        @Override
        public void keyTyped(KeyEvent e) {
            keyPressed(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_ENTER) {
                playGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Quit")) {
            System.exit(0);
        }
            Map<Object, Runnable> allActions = new HashMap<>(){{
                put(enterButton,()->playGame());
                put(restartButton,()-> {
                    System.out.println("Restarting...");
                    story.restartGame();
                    player.clearItems();
                    mainTextArea.setText(printStatus());
                    statusArea.setText("");
                });
                put(helpButton,()-> statusArea.setText("These are your commands:\n" +
                        "EXAMINE [Item] - to get the item description.\n" +
                        "GRAB [Item] - to add item to your inventory.\n" +
                        "DROP [Item] - to drop item from your inventory.\n" +
                        "USE [Item] - to use item in a scene.\n" ));
                put(startButton,()-> {
                    startButton.getParent().remove(startButton);
                    createGameScreen();
                    music.playSong();
                });
                put(musicButton,()-> {
                    if(music.isPlaying()){
                        music.pauseSong();
                    }else{
                        music.playSong();
                    }
                });
                put(nextButton,()-> createGameScreen());
            }};
            allActions.getOrDefault(e.getSource(),()->System.out.println("You have not selected a button.")).run();
        }
    }

    private String printStatus(){
        String status = "";
        status += (story.printStory() + "\n" + player.printGrabbedItems() + "\n" + player.printEvidence() + "\n" + story.printItems() + "\n" + game.printOptions());
        return status;
    }

    private void playGame(){
        try {
            String[] input = StringUtils.split(mainTextField.getText().toLowerCase().trim(), " ", 2);
            System.out.println("THIS IS THE INPUT" + mainTextField.getText());
            for (String s : input) {
                System.out.println(s);
            }
            final String[] Result = new String[1];
            Map<String, Runnable> allActions = new HashMap<>();

            ArrayList<Runnable> runners = new ArrayList<Runnable>(){{
                add(() -> {
                    if (story.hasItem(input[1]) || player.hasGrabbedItem(input[1])) {
                        Result[0] = item.getItemDescription(input[1]);
                    } else {
                        Result[0] = "You cannot examine that.\n";
                    }
                });
                add(() -> {
                    if (story.hasItem(input[1]) && !player.hasGrabbedItem(input[1])) {
                        if (player.grabItem(input[1])) {
                            story.setOptions(input[1]);
                            Result[0] = "You have grabbed " + input[1];
                        } else {

                            Result[0] = "You have too many items! Try dropping one if you really need to grab " + input[1];
                        }
                    } else {

                        Result[0] = "You cannot grab that.\n";
                    }
                });
                add(() -> {
                    String evidence = evidenceMap.get(input[1]);
                    if (player.hasGrabbedItem(input[1]) && story.hasHidden(evidence)) {
                        player.addEvidence(evidence);
                        player.dropItem(input[1]);
                        Result[0] = "You have used : " + input[1] + ", and you collected : " + evidence;
                    } else {
                        Result[0] = "You don't have this item in your inventory or your item does not work here";
                    }
                });
                add(() -> Result[0] = player.dropItem(input[1]));
            }};
            try{
                InputStream in = getClass().getResourceAsStream("/resources/validVerbs.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                final int[] counter = {0};
                reader.lines().forEachOrdered(synonyms -> {
                    String[] verbs = synonyms.split(",");
                    for(String verb : verbs){
                        allActions.put(verb,runners.get(counter[0]));
                    }
                    counter[0]++;
                });
            }catch(Exception except){
                System.out.println(except);
            }
            allActions.getOrDefault(input[0],()->{
                int answer = story.getOptions().values().stream().map(obj->obj.get("description").toString().toLowerCase()).collect(Collectors.toList()).indexOf(mainTextField.getText().toLowerCase().trim())+1;
                if (answer!=0) {
                    story.setCurrentOption(""+answer);
                    story.nextScene(true);
                    Result[0] =  "";
                } else {
                    Result[0] = "Not a command\n";
                }
            }).run();
            statusArea.setText(Result[0]);

            mainTextArea.setText(printStatus());
            mainTextField.setText("");
        } catch (ArrayIndexOutOfBoundsException exception) {
            statusArea.setText("Error: you didn't enter your move correctly");
        }
    }
}
