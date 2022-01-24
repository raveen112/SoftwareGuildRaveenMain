/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravee
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mathairm
 */
public class States {

    public static void main(String[] args) {
        Map<String, String> states = new HashMap<>();

        states.put("Alabama", "Montgomery");
        states.put("Alaska", "Juneau");
        states.put("Arizona", "Phoenix");
        states.put("Arkansas", "Little Rock");

        // Output only states
        Set<Map.Entry<String, String>> entries = states.entrySet();
        System.out.println("States: ");
        for(Map.Entry<String, String> entry : entries){
            System.out.println(entry.getKey());
        }
       
        // Only Capitals
        System.out.println("\n");
        System.out.println("CAPITALS: ");
        for(Map.Entry<String, String> entry : entries){
          System.out.println(entry.getValue());
        }
    
        // Both States/Capitals
        System.out.println("\n");
        System.out.println("STATE/CAPITAL PAIRS: ");
        for(Map.Entry<String, String> entry : entries){
            System.out.println(entry.getKey()+ " - " +entry.getValue());
            
        }
        
      

    }
}