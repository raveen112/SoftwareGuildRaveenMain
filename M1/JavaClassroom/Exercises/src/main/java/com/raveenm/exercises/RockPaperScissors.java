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
public class RockPaperScissors {
    public static void main(String[] args) {
        
        int[] scoreboard = new int[3];
        boolean play= false;
        
       do{                                                                     // do you wanna keep playing?
        System.out.println("How many rounds do you want to play? (1-10) ");   // input the rounds
        Scanner sc = new Scanner(System.in);
        int rounds = Integer.parseInt(sc.nextLine());                           
        
        
        //check if rounds input is valid
        if (rounds>0 && rounds<=10){                                            
            for(int i= 0; i <rounds; i++)
            {
                System.out.println("Rock = 1, Paper = 2, Scissors  = 3");             // input play
                int choice = Integer.parseInt(sc.nextLine());
                rockpaperscissors(choice, scoreboard);
                
                
                
                    
                }
            }
          else{
            System.out.println("You've entered an incorrect amount of rounds"); 
            
        }   
        // if statement with method to output the number of wins losses and ties
        // must declare an overall winner 
        
            System.out.println("Wins: " + scoreboard[0]);
            System.out.println("Ties: " + scoreboard[1]);
            System.out.println("Losses: "+ scoreboard[2]);
      
            if (scoreboard[0] >=scoreboard[1] && scoreboard[1]>scoreboard[2])
            {
                System.out.println("You beat me. Well done Padawan.");
            }
            else if(scoreboard[2]>scoreboard[0] && scoreboard[2]>scoreboard[0])
            {
                System.out.println("I win. You have much to learn young one.");
            }
            else if (scoreboard[0] == scoreboard[2] && scoreboard[2] == scoreboard[1])
                    {
                System.out.println("That's a tie. You must try again!");
            }
            else{
                System.out.println("That's a tie. You must try again!");
            }
           System.out.println("Do you want to play again (1- Yes/2 - No)? ");
           int playAgain= Integer.parseInt(sc.nextLine());
           
           
           if(playAgain==1){
               play=true;
           }
           else
           {
               play=false;
           }
               
    }while(play==true);
   }                                                                            // end of main
    
        
      
    public static void rockpaperscissors(int choice, int[] scores) {



        int x = (int) (Math.random() * ((3 - 1) + 1)) + 1;    // random computer decision

        if (choice == x) {
            System.out.println("Thats a tie!");
            scores[1]++;

        } else if (choice == 1 && x == 3) {
            System.out.println("Thats a win, computer chose Scissors");
            scores[0]++;

        } else if (choice == 2 && x == 1) {
            System.out.println("Thats a win, computer chose Rock");
            scores[0]++;
        } else if (choice == 3 && x == 2) {
            System.out.println("Thats a win, computer chose Paper");
            scores[0]++;
        } else if (choice == 1 && x == 2) {
            System.out.println("Thats a loss, computer chose Paper ");
            scores[1]++;
        } else if (choice == 2 && x == 3) {
            System.out.println("Thats a loss, computer chose Scissors");
            scores[2]++;
        } else if (choice == 3 && x == 1) {
            System.out.println("Thats a loss, computer chose Rock");
            scores[3]++;
        }

    }
        
        
    }

