/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class GuessMeFinally {
    public static void main(String[] args) {
        int a;
     
     Scanner sc = new Scanner(System.in);
     Random randomizer = new Random();
     
        int x = randomizer.nextInt(10 - - 10)+ -10;
        String answer;
        
       do{
        System.out.println("I've chosen a number. Betcha can't guess it!");
       
        a = Integer.parseInt(sc.nextLine());
        
        String s= Integer.toString(a);
        if(a==x)
        {
            System.out.println(s + "? Damn, you got it.");
            
        }
        if(a<x)
        {   
            System.out.println(s+ "? Too low!");
            
        }
        if(a>x)
            
        {
            System.out.println(s +"? Thats too high!");
            
        }
            System.out.println("Do you wanna try again? (y/n)");
            answer = sc.nextLine();
       } while (answer.equals("y"));
    }
}
    
   
