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
public class DontForgetToStore {
     public static void main(String[] args) {
         int meaningOfLifeAndEverything= 42;
         double pi = 3.14159;
         String cheese, color;
         
         Scanner inputReader = new Scanner(System.in);
         
         System.out.println("Give me pi boys:");
         Double.parseDouble(inputReader.nextLine());
         
         System.out.println("What is the meanign of the life universe and everything?");
         Integer.parseInt(inputReader.nextLine());
         
         System.out.println("Whats your fav kind of cheese?");
         cheese = inputReader.nextLine();
         
         System.out.println("Do you like red or blue?");
         color = inputReader.nextLine();
         
          System.out.println("Ooh, " + color + " " + cheese +" sounds delicious!");
          System.out.println("The circumference of life is " +( 2 * pi * meaningOfLifeAndEverything));
                    
         
     }
}
