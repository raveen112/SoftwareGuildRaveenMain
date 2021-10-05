/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.lists.ListExamples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ravee
 */
public class ListExample {
    public static void main(String[] args) {
        
    
     List <String> stringList = new ArrayList<>();
      
        stringList.add("The 1st String.");
        
        System.out.println("List size: " + stringList.size());
        
        stringList.add("The 2nd String.");
        
        System.out.println("List size: "+ stringList.size());
        
        stringList.remove(1);
        
        System.out.println("List size: "+ stringList.size());
        
        stringList.remove(0);
        
        System.out.println("List size: "+ stringList.size());
        
    }     
}
