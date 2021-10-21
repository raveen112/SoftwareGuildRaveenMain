/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.statecapitals2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class StateCapitals2 {

    public static void main(String[] args) throws Exception {
        Map<String, String> states = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader("StateCapitals.txt"));

        Scanner sc = new Scanner(System.in);

        String line;

        while (sc.hasNext()) {
            line = sc.nextLine();
            String[] parts = line.split("::", 2);
            //Array list = parts; 
            //.split splits the txt file values into 2 at the delimiter (0, 1)
            if (parts.length >= 2) {
                String key = parts[1];
                String value = parts[0];
                states.put(key, value);
            } else {
                System.out.println("ignoring line: " + line);
            }
        }
       
        System.out.println("Here are the states: ");
        for (String key : states.values()) {
            System.out.println("" + key);
        }

        //randomize the states
        List<String> stateList = new ArrayList<String>(states.values());
        int randomIndex;
        randomIndex = new Random().nextInt(stateList.size());
        String randomValue = stateList.get(randomIndex);

        System.out.println("Whats the capital of " + randomValue + "? ");
        String choice  = sc.nextLine();
        
        
        
        
        if(states.get(choice) == states.get(randomValue)){
           System.out.println("Thats right!");
            } else {
            System.out.println("Thats incorrect!");
            
        
        }
        

//        if (states.get(choice) == states.get(randomValue)) {
//            System.out.println("Thats right!");
//        } else {
//            System.out.println("Thats incorrect!");

        }

    }


