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
public class GuessMe {
    public static void main(String [] args){
     int a;
     
     Scanner sc = new Scanner(System.in);
     
        System.out.println("I've chosen a number. Betcha can't guess it!");
        a = Integer.parseInt(sc.nextLine());
       
        String s= Integer.toString(a);
        if(a==32)
        {
            System.out.println(s + "? Damn, you got it.");
            
        }
        if(a<32)
        {   
            System.out.println(s+ "? Too low! I guessed 32.");
            
        }
        if(a>32)
            
        {
            System.out.println(s +"? Thats too high! I guessed 32");
            
        }
    }
    }
   