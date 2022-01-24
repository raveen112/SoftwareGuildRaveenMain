/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class MiniZork {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.println("Go to the house, or open the mailbox? ");
        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in there.");
            System.out.print("Look inside or stick your hand in? ");
            action = userInput.nextLine();

            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.print("Run away or keep looking? ");
                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue.");
                } else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you alive. Possibly a wise choice.");
                }
                
                
            }else if (action.equals("stick your hand in")) 
            {
                System.out.println("You feel a prod and then a prick, you were bitten by a spider");
                System.out.println("Not very bright, are you?");
            }
        } else if (action.equals("go to the house")) 
        {
                System.out.println("You walk up to the front door next to which is a tall decrepid old ladder. ");
                System.out.println("Open the door or climb the ladder");
                action = userInput.nextLine();
               
               if(action.equals("open the door")){
                   System.out.println("A cold wind blows by you as you walk into the house.");
                   System.out.println("As you explore you chestt thats bursting with treasure and a library with all the worlds knowledge.");
                   System.out.println("Do you open the treasure chest or enter the library?");
                   action= userInput.nextLine();
                   
                    if(action.equals("open the treasure chest")){
                        System.out.println("You obtain the greatest riches in all the land! Congratulations!");
                    }
                    else{
                        System.out.println("You get decapitated by the owl that stands guard because you came in empty handed. D;");
                        
                    }
                    
               }
               else
               {
                   System.out.println("You climb the ladder half way and the ladder breaks.. not a very smart cookie. Told you the ladder was decrepid.");
                   
               }
               }
               
        }
    }

