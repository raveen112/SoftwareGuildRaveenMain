/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class BirthStones {
    public static void main(String[] args){
        
        int number ;
        
        Scanner sc = new Scanner (System.in);
        
        System.out.println("Enter the number of your month between 1-3");
        number = Integer.parseInt(sc.nextLine());
        
        switch (number) {
            case 1:
                System.out.println("January - Your birthstone is Garnet");
                break;
            case 2:
                System.out.println("February - Your birthstone is Amethyst!");
                break;
            default:
                System.out.println("Invalid Number!!!");
                break;
        }
    }
}
