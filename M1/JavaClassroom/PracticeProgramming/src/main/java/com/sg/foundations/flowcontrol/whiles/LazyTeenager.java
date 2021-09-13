/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

import java.util.Random;

/**
 *
 * @author ravee
 */
public class LazyTeenager {
    public static void main(String[] args) {
        int i =1 ;
        Random rng = new Random();
        int x = rng.nextInt(10)+ 1;
        
        
        do {
            if(x!=i)
            {
            System.out.println("Clean your room! !"+"(x"+ i+ ")" );
            i++;
            }
            else{
                System.out.println("Good job, Stanley!");
                break;
            }
            if(i==7){
              System.out.println("Clean your room! ! THAT'S IT, Im doing it!! You're grounded!");  
            }
        }while(i<7);
        
        
    }
}
