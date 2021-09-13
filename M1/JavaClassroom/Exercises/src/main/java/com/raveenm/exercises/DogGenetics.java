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
public class DogGenetics {
    public static void main(String[] args) {
        System.out.println("What is your dog's name? ");
        Scanner sc = new Scanner(System.in);
        
        String dogName= sc.nextLine();
        String[] dogNameMain = {"St.Bernard", "Chihuahua","Labradoodle", " Maltese", "Common folk" } ;  //dogbreed array
        
        System.out.println("Well then, I have this highly reliable report on "+ dogName + "'s prestigious background right here.");
        System.out.println(dogName + " is: ");
        
        Random random = new Random();
        
        int[] dogPercs = new int[dogNameMain.length] ;
        int sum = 0; 
        
        while(sum!=100){
            sum=0;
            for(int i=0; i<dogPercs.length; i++ ){
                int randomInt = random.nextInt(100)+1;
                dogPercs[i]=randomInt;
                sum +=randomInt;
            }
        }
            for(int i=0; i<dogNameMain.length; i++)
            {
                System.out.println(dogPercs[i] +"% " + dogNameMain[i]);
            }
        }
        
        
        
        
        
        
        
        
        /*for(int i = 0; i<=5; i++)
        {
            String perc[] = new String [6];
            int x = (int)(Math.random()*((5-1)+1))+1;
            int percentage= (x/100)*100;
            for(i=0; i<percentage; i++){
            percentage = perc[i];
        }*/
        
        
    }

        
        
        
           
    
    
    

