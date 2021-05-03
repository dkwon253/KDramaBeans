package com.kdramabeans.gui;

import com.kdramabeans.game.*;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame {
    private Player player = new Player();
    private Story story = new Story();
    private Game game = new Game(story, player, true);
    private BGM music = new BGM();
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
        titleNamePanel.setVisible(false);
        buttonPanel.remove(nextButton);
        buttonPanel.setVisible(true);
        container.remove(lblGif);

        // sets up the panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 50, 600, 450);
        mainTextPanel.setBackground(Color.white);

        // sets up the textArea
        mainTextArea = new JTextArea();
        mainTextArea.setText(printStatus());
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
    }

    public void displayGif() {
        titleNamePanel.setVisible(false);
        buttonPanel.remove(startButton);

        //String path = "/Users/kathyle27/Documents/GitHub/KDramaBeans/images/koreanair.gif";
        String path = "images/koreanair.gif";
        Icon imgGif = new ImageIcon(path);
        lblGif = new JLabel();
        lblGif.setIcon(imgGif);
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
            if (e.getSource() == enterButton) {
                playGame();
            } else if (e.getActionCommand().equals("Quit")) {
                System.exit(0);
            } else if (e.getSource() == restartButton) {
                System.out.println("Restarting...");
                story.restartGame();
                player.clearItems();
                mainTextArea.setText(printStatus());
                statusArea.setText("");
            } else if (e.getSource() == helpButton) {
                statusArea.setText("These are your commands:\n" +
                        "EXAMINE [Item] - to get the item description.\n" +
                        "GRAB [Item] - to add item to your inventory.\n" +
                        "CHOOSE [1/2/3] - select your option to go to next scene.\n" +
                        "DROP [Item] - to drop item from your inventory.\n" +
                        "USE [Item] - to use item in a scene.\n" );
            } else if (e.getSource() == nextButton) {
                createGameScreen();
            } else if (e.getSource() == startButton) {
                music.playSong();
                displayGif();
            } else if (e.getSource() == musicButton) {
                if(music.isPlaying()){
                    music.pauseSong();
                }else{
                    music.playSong();
                }
            }
            else {
                System.out.println("You have not selected a button.");
            }
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
            for (int index = 0; index < input.length; index++) {
                System.out.println(input[index]);
            }
            statusArea.setText(game.executeCommand(input, true));
            mainTextArea.setText(printStatus());
            mainTextField.setText("");
        } catch (ArrayIndexOutOfBoundsException exception) {
            statusArea.setText("Error: you didn't enter your move correctly");
        }
    }
}
