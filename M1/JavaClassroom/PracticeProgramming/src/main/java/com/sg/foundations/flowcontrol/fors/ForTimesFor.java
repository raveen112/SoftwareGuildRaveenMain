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
public class ForTimesFor {
    public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
        
        System.out.println("What times table shall I recite?");
        int c = Integer.parseInt(sc.nextLine());
        int result;
        int input;
        int counter=0;
        String s= Integer.toString(c);
        String count= Integer.toString(counter);
        for(int i =1; i<=3; i++)
        {
            result= i*c;
            System.out.println(i + "*" + s +"=");
            input= Integer.parseInt(sc.nextLine());
            if (input==result)
            {   
                System.out.println("Correct!");
                counter++;
            }
            else
            {
                System.out.println("Sorry, the answer is "+  result);
            }
           
    }
        
        System.out.println("You got "+ count + " right");
}
}