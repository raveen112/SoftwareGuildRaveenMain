/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

/**
 *
 * @author ravee
 */
public class LovesMe {
    public static void main(String[] args) {
      int i = 0;  
    
    do{
            System.out.println("It Loves ME!");
            System.out.println("It doesnt love me!");
            i++;
             
        }   while(i <= 34);
    
   if (i%2==0){
       System.out.println("IT LOVES MEE!");
   }
   else {
       System.out.println("Bummer </3");
   }
    }
        
    
}

