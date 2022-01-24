/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.fors;

/**
 *
 * @author ravee
 */
public class SpringForwardFallBack {
    public static void main(String[] args) {
       
        System.out.println("It's Spring...!");  // start 0 stop 10
        for(int i=0; i<10; i++){
            System.out.print(i+ ", ");
        }
        System.out.println("OH NO! Its fall..."); // start 10 stop 0
        for(int i=10 ; i>0; i--){
            System.out.print(i+ ",  ");
        }
    }
}
