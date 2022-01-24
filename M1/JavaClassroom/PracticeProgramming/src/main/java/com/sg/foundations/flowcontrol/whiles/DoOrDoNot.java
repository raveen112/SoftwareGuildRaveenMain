/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class DoOrDoNot {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Should I do it? (y/n)");
        boolean doIt;
        
        if(input.next().equals("y")){
            doIt = true;
            
        }
        else{
            doIt = false;
            
        }
        
        boolean iDidIt  = false;
        
        /*3. do {
            iDidIt = true;
            break;
            
        }while(doIt);*/
        
        while(doIt){
            iDidIt = true;
            break;
            
        }
        
        if(doIt && iDidIt){
            System.out.println("I did It!");
        }
        else if(!doIt && iDidIt){
            System.out.println("I know you said not to do it... but I totally did it anyways.");
        }
        else{
            System.out.println("I did nothing.");
           
            
            
        }
        
    }
}
// 1. I did it!
// 2. I know you said not to ... but I totally didI did it! anyways.
// 4. I did it!
// 5. I know you said not to ... but I totally didI did it! anyways.