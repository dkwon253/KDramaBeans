package com.kdramabeans.gui;

import com.kdramabeans.game.*;
import org.apache.commons.lang3.StringUtils;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;

public class GameFrame {
    private Player player = new Player();
    private Story story = new Story();
    private Game game = new Game(story, player);
    private BGM music = new BGM();
    private JFrame window;
    private JPanel titleNamePanel, buttonPanel, mainTextPanel, generalButtonPanel;
    private JLabel titleNameLabel, lblGif;
    private JButton startButton, nextButton, enterButton, restartButton, quitButton, helpButton;
    private JTextArea mainTextArea, statusArea;
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
        window.getContentPane().setBackground(Color.red);
        window.setLayout(null);
        window.setTitle("KDramaBeans Game");
        container = window.getContentPane();

        // Panel Title (can be used to place all the text and scenario we want to use)
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.blue);
        titleNameLabel = new JLabel("Welcome to KDramaBeans Game!");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        // start button setup - should link to the start of the game
        buttonPanel.setBounds(300, 400, 200, 100);
        buttonPanel.setBackground(Color.blue);
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
        mainTextPanel.setBounds(100, 50, 600, 350);
        mainTextPanel.setBackground(Color.blue);

        // sets up the textArea
        mainTextArea = new JTextArea();
        mainTextArea.setText(printStatus());
        mainTextArea.setBounds(100, 50, 600, 350);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);

        // enter button
        enterButton = new JButton("Enter");
        buttonPanel.setBounds(550, 350, 150, 100);
        enterButton.setBackground(Color.black);
        enterButton.setForeground(Color.black);
        enterButton.setFont(normalFont);
        enterButton.addActionListener(textHandler);
        buttonPanel.add(enterButton);
        generalButtons();

        // x,y,width,height

        // sets up the statusArea
        statusArea = new JTextArea();
        statusArea.setBounds(100, 475, 600, 250);
        statusArea.setBackground(Color.black);
        statusArea.setForeground(Color.white);
        statusArea.setFont(normalFont);
        statusArea.setLineWrap(true);

        // set up textField for userInput
        mainTextField = new JTextField();
        mainTextField.setBounds(100, 350, 450, 100);
        mainTextField.setBackground(Color.black);
        mainTextField.setForeground(Color.white);
        mainTextField.setFont(normalFont);
        mainTextField.addKeyListener(textHandler);

        mainTextPanel.add(mainTextArea);
        mainTextPanel.add(statusArea);
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

        generalButtonPanel.setBounds(100, 500, 600, 100);
        generalButtonPanel.add(quitButton);
        generalButtonPanel.add(helpButton);
        generalButtonPanel.add(restartButton);
        quitButton.addActionListener(textHandler);
        helpButton.addActionListener(textHandler);
        restartButton.addActionListener(textHandler);
        container.add(generalButtonPanel);
    }

    public class TitleScreenHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            music.playSong();
            displayGif();
        }
    }

    public class GifScreenHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createGameScreen();
        }
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
                System.out.println("These are your commands:\n" +
                        "EXAMINE + GRAB + CHOOSE + QUIT + RESTART + DROP\n");
            } else if (e.getSource() == nextButton) {
                createGameScreen();
            } else if (e.getSource() == startButton) {
//                music.playSong();
                displayGif();
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
            statusArea.setText(game.executeCommand(input));
            mainTextArea.setText(printStatus());
            mainTextField.setText("");
        } catch (ArrayIndexOutOfBoundsException exception) {
            statusArea.setText("Error: you didn't enter your move correctly");
        }
    }
}
