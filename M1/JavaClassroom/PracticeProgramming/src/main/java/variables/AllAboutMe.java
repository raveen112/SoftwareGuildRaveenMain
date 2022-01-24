/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variables;

/**
 *
 * @author ravee
 */
public class AllAboutMe {
    public static void main(String[] args){

        String name, favFood, pets, location, whistle;

        name = "Raveen";
        favFood = "Tender kerala beef fry.";
        pets = "I have 1 loveable dog.";
        location = "In a land far far away.";
        whistle = "It's true what they say about my whistle!";

        // Using the + with strings doesn't add -- it concatenates! (sticks them together)
        System.out.println("My name is " + name);
        System.out.println("I love a hot bowl of " +favFood);
        System.out.println(pets);
        System.out.println("I live " +location);
        System.out.println(whistle);
        
        }
        
        
    }
    
