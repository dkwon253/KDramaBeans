package com.kdramabeans.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame {
    private JFrame window;
    private JPanel titleNamePanel, startButtonPanel, mainTextPanel;
    private JLabel titleNameLabel;
    private JButton startButton;
    private JTextArea mainTextArea;
    private Container container;
    private static final Font titleFont = new Font("Times New Roman", Font.BOLD, 30);
    private static final Font normalFont = new Font("Times New Roman", Font.PLAIN, 15);

    TitleScreenHandler tsHandler = new TitleScreenHandler();

    /*
      ctor that initializes the home page of the game
     */
    public GameFrame() {
        window = new JFrame();
        titleNamePanel = new JPanel();
        startButtonPanel = new JPanel();
        startButton = new JButton("Start");

        // JFrame setup
        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
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
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.blue);
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);

        // calls up all the components and makes the screen visible
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        container.add(titleNamePanel);
        container.add(startButtonPanel);
        window.setVisible(true);
    }

    public void createGameScreen() {
        // disables to home page panel and will display panel below
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        // sets up the panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.blue);

        // sets up the textArea
        mainTextArea = new JTextArea("This story is about a man seeking revenge for the murder of his father. Your name is Park Ji Woo and you have become a successful entrepreneur in the states. You come back to South Korea under the alias of Daniel Park and your goal is to destroy X Company.");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
        container.add(mainTextPanel);
    }

    public class TitleScreenHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createGameScreen();
        }
    }
}