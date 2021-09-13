/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class BewareTheKraken {
    public static void main(String[] args) {
         int depthDivedInFt = 0;
         String answer; 
         Scanner sc = new Scanner (System.in);
        // Turns out the ocean is only so deep, 36200 at the deepest survey,
        // so if we reach the bottom ... we should probably stop.
       do{
        while(true)
        {
        System.out.println("So far, we've swum " + depthDivedInFt + " feet");
            
            if(depthDivedInFt >= 10000){
                System.out.println("Wow, were pretty damn deep!");
                System.out.println("Do you want to go deepeer? (y/n)");

            }
            
            if(depthDivedInFt == 10000)
            {
                System.out.println("Look its an Angler Fish!");
            }
            
            
                    
            if(depthDivedInFt >= 20000){
                System.out.println("Uhhh, I think i see a Kraken, guys....");
                System.out.println("Time TO GOOO!");
                break;
            }
            System.out.println("I can swim, really fast! 500ft at a time!"); 
            depthDivedInFt += 1000;

    }
        System.out.println("");
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");   
        System.out.println("Do you want to go keep going? (y/n)");
        answer= sc.nextLine();
        
   }while(answer.equals("y"));
}
}

