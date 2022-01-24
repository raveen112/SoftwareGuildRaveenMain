/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class StayPositive {
    public static void main(String[] args) {
        int start;
        
        Scanner sc = new Scanner (System.in);
      
           System.out.println("Enter a number to start: ");
           start = Integer.parseInt(sc.nextLine());
           
        if (start<=0)
        {
            System.out.println("Incorrect number, fool!");
        }
        System.out.println("Couting down...");
       
        while(start>=0){
            
            System.out.print(start);
            start--;
            }
            
        }
        
    }
    
    

