package com.kdramabeans.game;
import com.kdramabeans.gui.GameFrame;
public class Main {
    public static void main(String[] args){
        try{
            new GameFrame();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
