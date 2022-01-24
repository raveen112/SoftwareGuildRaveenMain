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
public class Opinionator {
    public static void main(String[] args) {
        
        Random randomizer = new Random();
        System.out.println("I can't decide what animal I like the best.");
        System.out.println("I know! Random can decide FOR ME!");
        
        int x = randomizer.nextInt(5);
        
        System.out.println("The number we chose was: " +x);
        
        
        switch(x){
            case 0:
                System.out.println("Llamas are the best!");
                break;
            case 1:
                System.out.println("Dodos are the best!");
                break;
            case 2:
                System.out.println("Elephants are definitely the best!");
                break;
            case 3:
                System.out.println("Pokemon are genetically superior!");
                break;
            case 4:
                System.out.println("Lol I love candy!");
                break;
            case 5:
                System.out.println("Have you heard of Ice Rats?");
                break;
        }
        System.out.println("Thanks Random!");
        
                
             
               
        }
        
        
        
 }

