/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class MiniMadLibs {
    public static void main(String[] args){
        String noun1, noun2, adjective1, adjective2, pNoun, noun3, noun4, verb1, verb2;
        int numberl;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Lets play MAD LIBS!");
        
        System.out.println("I need a noun: ");
        noun1= inputReader.nextLine();
        
        System.out.println("Now an adjective: ");
        adjective1= inputReader.nextLine();
        
        System.out.println("I need another noun: ");
        noun2= inputReader.nextLine();
        
        System.out.println("I need a number: ");
        numberl= Integer.parseInt(inputReader.nextLine());
        
        System.out.println("Now another adjective: ");
        adjective2= inputReader.nextLine();
        
        System.out.println("Plural noun please: ");
        pNoun= inputReader.nextLine();
        
        System.out.println("I need another noun: ");
        noun3= inputReader.nextLine();
        
        System.out.println("Another noun: ");
        noun4= inputReader.nextLine();
        
        System.out.println("I need a verb(infinitve form): ");
        verb1= inputReader.nextLine();
        
        System.out.println("I need a verb(past participle): ");
        verb2= inputReader.nextLine();
     
        
        String s= Integer.toString(numberl);
        
        System.out.println( noun1 + " the " + adjective1 + "frontier. " + "Tnese are the voyages of the starship " + noun2 + ". "+ "It's " + s +"-year mission: to explore strange " +adjective2+ " " + noun3 + " " +pNoun + "to boldly " + verb1 + "where no one has " + verb2 +"before.");
      
    }
}
