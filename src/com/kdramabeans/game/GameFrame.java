package com.kdramabeans.game;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    JFrame window;
    Container container;
    JPanel titleNamePanel, startButtonPanel;
    JLabel titleNameLabel;
    Font titleFont = new Font("Times New Roman", Font.BOLD, 90);

    public GameFrame() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        container = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);

        titleNameLabel = new JLabel("KDramaBean");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

//        startButtonPanel = new JPanel();
//        startButtonPanel.setBounds(300, 400, 200, 100);
//        startButtonPanel.setBackground(Color.blue);

        titleNamePanel.add(titleNameLabel);

        container.add(titleNamePanel);
//        container.add(startButtonPanel);
    }
}
