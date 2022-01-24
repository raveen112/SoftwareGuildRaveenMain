/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.fors;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class FizzBuzz {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("How many units of fizzing and buzzing do you need in your life? ");
    int input = Integer.parseInt(sc.nextLine());
    int counter=0;
    
    for(int i=1; i<=100; i++)
    {
        if(i%3==0 && i%5==0)
        {
            System.out.println("fizz buzz");
            counter++;
        }
        else if(i%5==0)
        {
            System.out.println("buzz");
            counter++;
        }
        else if(i%3==0)
        {
            System.out.println("fizz");
            counter++;
        }
            
        
        else if(counter == input)
        {  
            System.out.println("TRADITION!!!");
            break;
        }
        else{
            System.out.println(i);
        }
        
    }
    
    }
}
