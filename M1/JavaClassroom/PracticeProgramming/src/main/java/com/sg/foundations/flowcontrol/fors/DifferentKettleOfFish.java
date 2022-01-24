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
public class DifferentKettleOfFish {
    public static void main(String[] args) {
        int fish;
        
        for(fish=0; fish<=10; fish++ )
        {
            if(fish==3){
                System.out.println("RED fish");
                
            }
            else if(fish==4)
            {
                System.out.println("BLUE fish");
                
            }
            else
            {
                System.out.println(fish + " fish!");
            }
        }
    }
}
// the range was set in the for loop and the if statements were inside the for loop