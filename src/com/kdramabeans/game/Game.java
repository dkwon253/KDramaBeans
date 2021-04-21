package com.kdramabeans.game;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Game {
    Player player = new Player();

    public static void main(String[] args){
        System.out.println("Enter an item: ");
        Scanner scanner = new Scanner(System.in);
        Items items = new Items();

        String input = StringUtils.capitalize(scanner.nextLine());
        System.out.println(items.getItemDesc().get(input));
    }
}
