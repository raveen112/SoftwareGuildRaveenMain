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
public class TheCount {
    public static void main(String[] args) {
        int i;
        int start, stop, inc;
        
        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        Scanner sc = new Scanner(System.in);
        System.out.println("Start at: ");
        start= Integer.parseInt(sc.nextLine());
        System.out.println("Stop at: ");
        stop = Integer.parseInt(sc.nextLine());
        System.out.println("Count by: ");
        inc = Integer.parseInt(sc.nextLine());
        
        for(i = start; i<=stop ; i+=inc){
            System.out.print(i + ", ");
        }
          
    }
    
}
