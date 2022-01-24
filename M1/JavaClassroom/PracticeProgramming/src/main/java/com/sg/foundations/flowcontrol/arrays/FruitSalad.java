/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;

/**
 *
 * @author ravee
 */
public class FruitSalad {
    public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};

        String[] fruitSalad;
        fruitSalad = new String[12];
        int fruitI = 0;
        int saladI = 0;
        int oranges = 0;
        int apples = 0;
        
        // go through the array first time and add all the berries we can
        for (int i=0; i < fruit.length; i++) {
            String currentFruit = fruit[i];
            String currentFruitLC = currentFruit.toLowerCase();
            if (currentFruitLC.contains("berry")) {
                fruitSalad[saladI] = currentFruit;
                saladI++;
            }
        }
        
        // go through the array again and pick other fruits
        // continue at the fruitSalad index (saladI) we ended up with in the first loop
        while (saladI < 12 && fruitI < fruit.length) {
            String currentFruit = fruit[fruitI];
            String currentFruitLC = currentFruit.toLowerCase();
            // we don't want tomatoes and we already added all the berries
            if (!(currentFruitLC.contains("berry") || currentFruitLC.contains("tomato"))) {
                if (currentFruitLC.contains("apple") && apples < 3) {
                    fruitSalad[saladI] = currentFruit;
                    apples++;
                    // only increment saladI when a fruit is added to salad
                    saladI++;
                } else if (currentFruitLC.contains("orange") && oranges < 2) {
                    fruitSalad[saladI] = currentFruit;
                    oranges++;
                    saladI++;  
                } else if (!(currentFruitLC.contains("orange") || currentFruitLC.contains("apple"))){
                    fruitSalad[saladI] = currentFruit;
                    saladI++;
                }
            }
            fruitI++;
        }
        
        // code below fills up fruitSalad but doesn't take all the berries we can get (as per instructions)
        /*
        while (saladI < 12 && fruitI < fruit.length ) {
            String item = fruit[fruitI].toLowerCase();
            if (item.contains("berry")) {
                fruitSalad[saladI] = item;
                saladI++;
            } else if (item.contains("apple") && apples < 3) {
                fruitSalad[saladI] = item;
                saladI++;
                apples++;
            } else if (item.contains("oranges") && oranges < 2) {
                fruitSalad[saladI] = item;
                saladI++;
                oranges++;
            } else if (!(item.contains("tomato") || item.contains("apple") || item.contains("orange"))) {
                fruitSalad[saladI] = item;
                saladI++;
            }
            fruitI++;
        }
        */
        for (int i = 0; i < fruitSalad.length; i++) {
           System.out.println(fruitSalad[i]);
        }
        
    }
}