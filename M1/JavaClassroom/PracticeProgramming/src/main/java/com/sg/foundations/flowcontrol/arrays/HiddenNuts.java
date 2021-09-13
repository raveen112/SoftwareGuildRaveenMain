/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;

import java.util.Random;

/**
 *
 * @author ravee
 */
public class HiddenNuts {
    public static void main(String[] args) {
        
        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        int nutI=0;
        hidingSpots[squirrel.nextInt(hidingSpots.length)] = "Nut";
        System.out.println("The nut has been hidden ...");
        for(int i=0; i<hidingSpots.length; i++){
            String currentSpot = hidingSpots[i];
            if(currentSpot != null && currentSpot.contains("Nut")){
                nutI = i;
                  System.out.println("The nut is in position "+ nutI);          
            }
        }
         

    }
}
