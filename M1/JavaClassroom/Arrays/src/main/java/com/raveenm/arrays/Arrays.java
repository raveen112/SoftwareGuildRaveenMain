/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.arrays;

/**
 *
 * @author ravee
 */
public class Arrays {
    
    public static void main(String[] args) {
        int [] teamScores;
        
        teamScores= new int[5];
        
        teamScores[0] = 12;
        teamScores[1] = 54;
        teamScores[2] = 7;
        teamScores[3] = 3;
        teamScores[4] = 2;
        
        int[] golfScores = {23, 8, 6, 36, 7};
        
        int currentGolfScores = golfScores[0];
        
        golfScores[2] = 70;
        
        for(int i =0; i < golfScores.length; i++)
        {
             System.out.println(golfScores[i]);
             
        }
        for(int currentScore: golfScores){
            System.out.println(currentScore);
        }
    }
}
