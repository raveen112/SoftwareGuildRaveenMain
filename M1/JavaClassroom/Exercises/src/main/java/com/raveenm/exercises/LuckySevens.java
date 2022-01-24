/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.exercises;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class LuckySevens {
       public static void main(String[] args) {
        // ask user for input
        int currentMoney ;
        Scanner sc = new Scanner(System.in);
        System.out.println("How much do you want to bet? ");
        currentMoney = Integer.parseInt(sc.nextLine());
        int maxMoney = currentMoney;
        int totalRolls = 0;
        int maxMoneyRoll = 0;
        //roll dice till money is gone
        while(currentMoney>0)
        {
        
                 //roll 2 dice
            int diceTotal = RollDice() + RollDice();
            //track dice rolls
            totalRolls++;
                 // if total is 7, +4$
            if(diceTotal ==7)
            {
                currentMoney +=4;
            
            // track max money
             if(currentMoney > maxMoney){
                    maxMoney =  currentMoney;
                    maxMoneyRoll = totalRolls;
             }
            }
            //else -1$
            else
            {
                currentMoney -=1;
            }
               
        }
                 
        System.out.println("You are broke after: " + totalRolls);
        
        System.out.println("You should have quit after " + maxMoneyRoll + " rolls, when you had " + maxMoney);
        
        
    }
        
        // track max money
        // track what roll the player had max money
        
            
    
    
    public static int RollDice(){
        Random rng = new Random();
        return rng.nextInt(6);
        
}
}