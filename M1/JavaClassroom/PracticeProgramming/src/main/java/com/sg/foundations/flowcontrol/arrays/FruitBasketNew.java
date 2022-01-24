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
public class FruitBasketNew {
    public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};

        String[] fruitSalad;
        fruitSalad = new String[12];
        int saladTemp = 0;
        int fruitTemp = 0;
        int oranges = 0;
        int apples = 0;
        
        
        //add all the berries
        for(int i=0; i<fruit.length;i++ )
        {
            String currentFruit = fruit[i];
            String currentFruitLower= currentFruit.toLowerCase();
            if(currentFruitLower.contains("berry"))
            {
                fruitSalad[saladTemp] = currentFruit;
                saladTemp++;
            }
        }
        
        while(saladTemp<12 && fruitTemp<fruit.length)
        {
            String currentFruit = fruit[fruitTemp];
            String currentFruitLower= currentFruit.toLowerCase();
            
            if(!(currentFruitLower.contains("berry")|| currentFruitLower.contains("tomato")))
            {
                if(currentFruitLower.contains("apple") && apples<3)
                {
                    fruitSalad[saladTemp] = currentFruit;
                    apples++;
                    saladTemp++;
                }
                else if(currentFruitLower.contains("orange")&& oranges<2)
                {
                    fruitSalad[saladTemp] = currentFruit;
                    oranges++;
                    saladTemp++;
                        
                }
                else if(!(currentFruitLower.contains("orange") || currentFruitLower.contains("apple"))){
                    fruitSalad[saladTemp]= currentFruit;
                    saladTemp++;
                }
                    
            }
            fruitTemp++;
        }
             for (int i = 0; i < fruitSalad.length; i++) {
                System.out.println(fruitSalad[i]);
             }
            }
        }

