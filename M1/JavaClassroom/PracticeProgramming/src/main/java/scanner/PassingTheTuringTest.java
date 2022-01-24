/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class PassingTheTuringTest {
    public static void main (String[] args){
        Scanner inputReader = new Scanner (System.in);
   
        String name;
        String color;
        String fruit;
        float favNumber;
        
        System.out.println("Hello There!");
        System.out.println("Whats your name?");
        name = inputReader.nextLine();
        
        System.out.println("Hello, " + name +"! " + "My name is Banksy");
        
        System.out.println("Do you have a favorite color?");
        color = inputReader.nextLine();
        
        System.out.println("Huh, "+ color + "? " +"Mine is Electric Purple");
        
        System.out.println("I really like yams. They're my favorite root vegetable");
        
        System.out.println("Do you have a favorite vegetable?");
        fruit = inputReader.nextLine();
        
        System.out.println("Really??" + fruit + "?" + "That's CRAZY!");
        
        System.out.println("Speaking of favorites, do you have a favorite number?");
        favNumber = Integer.parseInt(inputReader.nextLine());
        
        System.out.println(favNumber + "is a good one!" + "Mine is -7.");
        float multiplyFactor = favNumber * (-7);
        
        System.out.println("Did you know "+ favNumber + "x -7 " +" is " +multiplyFactor +" Thats a cool number too!");
        
        System.out.println("Thanks for talking to me, "+ name);
        
             
        
        
        
        
        
        
        
        
    }
    
}
