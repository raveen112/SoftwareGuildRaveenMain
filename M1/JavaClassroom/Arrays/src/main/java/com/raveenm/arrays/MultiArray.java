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
public class MultiArray {
    public static void main(String[] args) {
        
        String[][] cityTeamNames = {
            {"Cleveland", "Browns", "Cavs", "Indians"},
            {"Columbus", "BlueJackets", "Buckeyes"},
            {"Pittsburgh", "Penguins", "Steelers", "Pirates"}
        };
        
        for(int i = 0 ; i < cityTeamNames.length ; i++){
            for(int j=0; j<cityTeamNames[i].length; j++){
                System.out.print(cityTeamNames[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
