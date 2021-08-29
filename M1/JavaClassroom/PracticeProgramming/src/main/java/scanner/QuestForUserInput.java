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
public class QuestForUserInput {
    public static void main(String[] args){
        Scanner inputReader = new Scanner(System.in);
        
        String yourName;
        String yourQuest;
        double velocityOfSwallow;
        
        System.out.println("What is your name?");
        yourName = inputReader.nextLine();
        
        System.out.println("What is your quest?");
        yourQuest = inputReader.nextLine();
        
        System.out.println("What is the airspeed of an unladen swallow?");
        velocityOfSwallow = Double.parseDouble(inputReader.nextLine());
        System.out.println();
        System.out.println("Wrong you didnt even ask of the birds origins.");
        System.out.println("Maybe skip answering things and go back " +yourQuest);
        
       
        
    }
}
