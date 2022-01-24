/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.exercises;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class Factorizer {
     public static void main(String args[]){
      int number;
      Scanner sc = new Scanner(System.in);
      System.out.println("What number would you like to factor? ");
      number = sc.nextInt();
      
      System.out.println("The factors of "+ number + " are: ");   
      System.out.println(factor(number)+ " ");
      System.out.println(count(number));
      System.out.println(perfect(number));
      
      if(prime(number)==true)
      { 
          System.out.print("It is a prime number");
          
      }
      else
      {
          System.out.print("It is not a prime number.");
      }
      
     }
     
     
      public static boolean prime(int number){
          
        if (number <= 1)
            return false;
        if (number <= 3)
            return true;
        
        if (number % 2 == 0 || number % 3 == 0)
            return false;
        
      for(int i = 5; i*i< number;i= i+6) 
          if (number % i == 0 || number % (i + 2) == 0)
                return false;
         
         return true;
      
     }
    
      public static int factor(int number){
         int count=0;
         int sum= 0;
         for(int i = 2; i< number; i++) {
             {
                 if(number%i==0){
                     System.out.println(i);
                 }
             }
         }
                  
          
         return number;
      }
      
      public static int perfect(int number){
         int count=0;
         int sum= 0;
         for(int i = 1; i< number; i++) {
             
                 if(number%i==0)
                     sum= sum+i;
                 
             
         }
                 if(sum == number)
                     System.out.println(number + " is a perfect number.");
                     
                 
                 else
                     System.out.println(number + " is NOT a perfect number.");
      return 1;
     
}    
      
      public static int count(int number){
         int count=0;
         for(int i = 1; i< number; i++) 
             
                 if(number%i==0)
                 count++;
            
             System.out.println(number + " has " + count + " factors." );
      
    
           
      return count;
      }
}



             
          
      
   

