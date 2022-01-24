/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.windowmaster;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class WindowMaster {
    public static void main(String[] args){
        float height ;
        float width;
        
        float areaOfWindow;
        float cost;
        float perimeterOfWindow;
        
        // delcare and initialize the scanner
        

        
        // obtain inputs
        height = readValue("Please enter window height: ");
        width = readValue("Please eneter window width: ");
        
        //area of window
        areaOfWindow= height*width;
        
        //perimeter of window
        perimeterOfWindow= 2 * (height+width);
        
        cost = ((3.50f*areaOfWindow)+(2.25f*perimeterOfWindow));
     
     // display the results to the user
    System.out.println("Window height = " + height);
    System.out.println("Window width = " + width);
    System.out.println("Window area = " + areaOfWindow);
    System.out.println("Window perimeter = " + perimeterOfWindow);
    System.out.println("Total Cost =  " + cost);
}
    
    public static float readValue(String prompt){
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();
        float floatVal = Float.parseFloat(input);
        return floatVal;
    }  
}
