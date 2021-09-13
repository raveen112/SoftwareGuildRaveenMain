/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class TriviaNight {
    public static void main(String[] args){
        int answer1, answer2, answer3;
        int score=0;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("It's TRIVIA NIGHT! Are you ready?!");
        
        System.out.println("FIRST QUESTION!");
        System.out.println("What is the Lowest Level Programming Language?");
        System.out.println("1) Source Code		2) Assembly Language\n" +
"3) C#			4) Machine Code");
        
        answer1=Integer.parseInt(sc.nextLine());
        
        if(answer1 == 4)
        {   
           score= score +1;                                      // score counter
           System.out.println("Your answer was " +answer1);
        }
        else 
        {
           System.out.println("Your answer was " +answer1);  
        }
        
        System.out.println("SECOND QUESTION!");
        System.out.println("Website Security CAPTCHA Forms Are Descended From the Work of?");
        System.out.println("1) Grace Hopper		2) Alan Turing\n" +
"3) Charles Babbage	4) Larry Page");
        answer2=Integer.parseInt(sc.nextLine()); 
        
         if(answer2 == 2)
        {   
           score= score +1;
           System.out.println("Your answer was " +answer2);
        }
        else 
        {
           System.out.println("Your answer was " +answer2);  
        }
        
        System.out.println("THIRD QUESTION!");
        System.out.println("Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.println("1) Serenity			2) The Battlestar Galactica\n" +
"3) The USS Enterprise	4) The Millennium Falcon");
        answer3=Integer.parseInt(sc.nextLine());
        
         if(answer3 == 3)
        {   
           score= score +1;
           System.out.println("Your answer was " +answer3);
        }
        else 
        {
           System.out.println("Your answer was " +answer3);  
        }
        
         if(score==3)
         {
         System.out.println("Excellent! Your score is " + score);
    }
         if(score==0)
         {
         System.out.println("Aww bad luck! Your score is " + score);
         }
         else
         {
         System.out.println("Nice one! Your score is "+ score);
         }
    }
    
    
} 
