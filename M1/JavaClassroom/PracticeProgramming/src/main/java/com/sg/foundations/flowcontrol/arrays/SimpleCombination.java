/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;

import java.util.Arrays;

/**
 *
 * @author ravee
 */
public class SimpleCombination {
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 35, 45, 47, 49};
        int[] secondHalf = {51, 54, 68, 71, 75, 78, 82, 84, 85, 89, 91, 100};
        
        
        int[] wholeNumbers = new int[24];
        int temp = 0;
        
        System.out.println("All together now! :");
        for(int i = 0; i<firstHalf.length; i++){
            wholeNumbers[i] = firstHalf[i];
            temp++;
        }
        for(int j = 0; j<secondHalf.length; j++){
            wholeNumbers[temp++] = secondHalf[j];
        }
        
        for(int i =0; i<wholeNumbers.length; i++)
        {
            System.out.print(wholeNumbers[i] + " ");
        }
        
        
        
       /* System.out.println("All together now! ");
        System.arraycopy(firstHalf, 0, wholeNumbers, 0, a);
        System.arraycopy(secondHalf, 0, wholeNumbers, a, b);
        System.out.println(Arrays.toString(wholeNumbers));*/
        
                
            }
        }
  
  

