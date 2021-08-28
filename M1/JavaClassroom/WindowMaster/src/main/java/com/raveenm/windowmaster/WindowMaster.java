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
        
        String stringHeight;
        String stringWidth;
        
        float areaOfWindow;
        float cost;
        float perimeterOfWindow;
        
        // delcare and initialize the scanner
        
        Scanner myScanner = new Scanner(System.in);
        
        // obtain inputs
        System.out.println("Please enter window height: ");
        stringHeight = myScanner.nextLine();
        System.out.println("Please enter window width: ");
        stringWidth = myScanner.nextLine();
        
        // string conversion
        height = Float.parseFloat(stringHeight);
        width = Float.parseFloat(stringWidth);
        
        areaOfWindow= height*width;
        
        perimeterOfWindow= 2 * (height+width);
        
        cost = ((3.50f*areaOfWindow)+(2.25f*perimeterOfWindow));
     
// display the results to the user
    System.out.println("Window height = " + stringHeight);
    System.out.println("Window width = " + stringWidth);
    System.out.println("Window area = " + areaOfWindow);
    System.out.println("Window perimeter = " + perimeterOfWindow);
    System.out.println("Total Cost =  " + cost);
}
}
