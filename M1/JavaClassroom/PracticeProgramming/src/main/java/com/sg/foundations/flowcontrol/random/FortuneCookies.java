/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.random;

import java.util.Random;

/**
 *
 * @author ravee
 */
public class FortuneCookies {
    public static void main(String[] args) {
        
        Random rng = new Random();
        
        int x = rng.nextInt(6);
        
        System.out.println("Your Geek Fortune: " + x);
        
        switch(x){
            case 1:
                System.out.println("I am your daddy, Martha!");
                break;
             
            case 2:
                System.out.println("Facts dont care about your feelings!");
                break;
                
            case 3:
                System.out.println("I'm Mary Poppins Y'all! ;_;");
                break;
                
            case 4:
                System.out.println("Live long enough to become the villain.");
                break;
                
            case 5:
                System.out.println("You are a leaf on the wind, watch how you soar.");
                break;
        }
        
       }
        
    }
    
