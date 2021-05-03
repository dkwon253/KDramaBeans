package com.kdramabeans.game;

import com.kdramabeans.gui.GameFrame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        boolean userSelection = false;
        System.out.println("Choose your game interface. 1. Console 2. GUI");
        String options = input.nextLine().trim();
        while(!userSelection){
            if(options.equals("1")){
                Game game = new Game();
                game.start();
                userSelection = true;
            } else if(options.equals("2")){
                new GameFrame();
                userSelection = true;
            } else{
                System.out.println("That's not an option");
                options = input.nextLine().trim();
            }
        }
    }
}
