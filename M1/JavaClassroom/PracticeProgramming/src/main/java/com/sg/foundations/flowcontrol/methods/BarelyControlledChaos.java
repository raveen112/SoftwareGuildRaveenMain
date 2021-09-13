/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.methods;

import java.util.Random;

/**
 *
 * @author ravee
 */
public class BarelyControlledChaos {
    public static void main(String[] args) {
        String color = color("");
        String animal = animal(""); // call animal method again here
        String colorAgain = color(""); // call color method again here
        
        int weight = num(5, 200); // call number method,
            // with a range between 5 - 200
        int distance = num(10, 20); // call number method,
            // with a range between 10 - 20
        int number = num(10000, 20000); // call number method,
            // with a range between 10000 - 20000
        int time = num(2, 6); // call number method,
            // with a range between 2 - 6            

        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
            + number + " " + colorAgain + " poppies for nearly "
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
            + "let me tell you!");
        
     
        

    }
    
    
    public static String color(String prompt){
        int s ;
         
        Random rng = new Random();
        s = rng.nextInt(5);
       
        
        if(s ==1){
            return "blue";
        }
        else if(s==2)
        {
            return "green";
        }
         else if(s==3)
        {
            return "black";
        }
         else if(s==4)
        {
            return "purple";
        }
         else 
        {
            return "ice lilac";
        }
        
    }

        
        public static String animal(String prompt){
        int animal=0 ;
         
        Random rng = new Random();
        animal = rng.nextInt(5);
        
       
        
        if(animal==1){
            return "cow";
        }
        else if(animal==2)
        {
            return "dragon";
        }
         else if(animal==3)
        {
            return "leprechaun";
        }
         else if(animal==4)
        {
            return "goblin";
        }
         else if(animal==5)
        {
            return "ice cream man";
        }
        return null;
    }
        
        public static int num(int min, int max){
    
           int x = (int)(Math.random()*((max-min)+1))+min;
           return x;
        }
       
    
}


           
        
        
        
 

