/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.studentquizscores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ravee
 */
public class App {

    public static void main(String[] args) {

        UserIO io = new UserIOImpl();

        Map<String, List<Integer>> students = new HashMap<>();

        boolean keepGoing = true;

        do {

            io.print("What would you like to do? ");
            io.print("1. View Students ");
            io.print("2. Add Student ");
            io.print("3. Remove Student");
            io.print("4. View Quiz Scores ");
            io.print("5. Average Scores of a Student");
            io.print("6. Average Scores of Class");
            io.print("7. Best and worst student");

            int choice = io.readInt("Enter your choice (1-7) ", 1, 7);

            switch (choice) {

                case 1:
                    Collection<String> studentNames = students.keySet();
                    for (String name : studentNames) {
                        io.print(name);
                    }
                    break;

                //Add student and choice for grades
                case 2:

                    String student = io.readString("Enter Student Name: ");
                    int grades1 = io.readInt("Add 1st grade to the student: ");
                    int grades2 = io.readInt("Add 2nd grade to the student: ");
                    int grades3 = io.readInt("Add 3rd grade to the student: ");

                    List<Integer> scoresNew = new ArrayList<>();

                    scoresNew.add(grades1);
                    scoresNew.add(grades2);
                    scoresNew.add(grades3);

                    students.put(student, scoresNew);

                    break;

                case 3:

                    student = io.readString("Enter the student you wish to remove: ");
                    List<Integer> scores = students.remove(student);
                    if (scores == null) {
                        io.print("Student does not exist! ");
                    } else {
                        io.print("Succesfully removed student.");
                    }

                    break;

                //
                case 4:

                    student = io.readString("Enter Student Name: ");
                    scores = students.get(student);
                    if (scores == null) {
                        io.print("Student does not exist! ");
                    } else {
                        io.print("Grades: ");
                        for (int score : scores) {
                            io.print("" + score);
                        }
                    }
                    break;

                case 5:
                    student = io.readString("Enter student name:  ");
                    scores = students.get(student);
                    int sum = 0;
                    for (int score : scores) {
                        sum = sum + score;
                    }

                    float average = sum / scores.size();

                    io.print("Average:" + average);

                case 6:
                    float totalAverage = 0;

                    //value set of grades
                    Collection<String> studentWhole = students.keySet();

                    for (String name : studentWhole) {
                        scores = students.get(name);
                        sum = 0;
                        for (int score : scores) {
                            sum = sum + score;

                        }

                        average = sum / scores.size();

                        totalAverage += average;
                    }
                    
                    
                    io.print("Class Average: " + totalAverage / studentWhole.size());
                    break;

            
            case 7:
                
                String bestStudent = null;
                String worstStudent = null;
                
                float bestAverage = 0;
                float worstAverage = 100;
                
                studentWhole = students.keySet();

                    for (String name : studentWhole) {
                        scores = students.get(name);
                        sum = 0;
                        for (int score : scores) {
                            sum = sum + score;
                        }
                          
                        average = sum / scores.size();
                        
                        if(average > bestAverage){
                            bestAverage = average;
                            bestStudent = name;
                        }
                        if(average < worstAverage){
                            worstAverage = average;
                            worstStudent = name;
                        }
                        
                    }
                    
                    io.print("The best student is: " + bestStudent);
                    io.print("The worst student is: "+ worstStudent);

                    break;
            }
                        
            // create a name hash map
            // names would be the key
            // scores would be an array list
            // List <Integer> scoreList1 = new ArrayList<>(Arrays.asList(92,86,77,82));
            // calculate average scores
            // options 2to display hashmap (Switch case)
            // create a name hash map

            // continue program 
            String userResponse = io.readString("Would you like to continue (y/n)? ");
            if (userResponse.equals("n")) {
                keepGoing = false;
            }

        } while (keepGoing);
    }
}

