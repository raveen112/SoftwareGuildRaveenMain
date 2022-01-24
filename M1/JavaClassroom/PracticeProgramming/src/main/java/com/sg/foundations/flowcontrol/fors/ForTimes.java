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
public class ForTimes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What times table shall I recite?");
        int c = Integer.parseInt(sc.nextLine());
        int result;
        String s= Integer.toString(c);
        for(int i =1; i<=15; i++)
        {
            result= i*c;
            System.out.println(i + "*" + s +"=" + result );
        }
    }
    
}
