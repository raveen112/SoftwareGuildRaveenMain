/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.classroster.ui;

import com.raveenm.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author ravee
 */
public class ClassRosterView {
    private UserIO io;
    
    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. List Students");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    public Student getNewStudentInfo(){
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First name");
        String lastName = io.readString("Please enter Last name");
        String cohort = io.readString("Please enter cohort");
        Student currentStudent = new Student (studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
    }
    
    public void displayCreateStudentBanner(){
        io.print("=== Create Student ===");
    }
    
    public void displayCreateSuccessBanner(){
        io.readString("Student successfully created. Please hit enter to continue");
    }
    
    public void displayStudentList(List<Student> studentList){
        for (Student currentStudent : studentList){
            String studentInfo = String.format("#%s : %s %s", currentStudent.getStudentId(), currentStudent.getFirstName(), currentStudent.getLastName());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner(){
        io.print("=== Display All Students ===");
    }
    
    public void displayDisplayStudentBanner(){
       io.print("=== Display Student ===");
       
    }
    
    public String getStringIdChoice(){
        return io.readString("Please enter the Student ID");
    }
    
    public void displayStudent(Student student){
        if (student != null){
            io.print(student.getFirstName());
            io.print(student.getLastName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print(" ");
        }else {
            io.print("Student doesn't exist!");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayRemovedStudentBanner(){
        io.print("=== Remove Student === ");
    }
    
    public void displayRemoveStudentResult(Student studentRecord){
        if(studentRecord != null){
            io.print("Student successfully removed.");
        }
        else
        {
            io.print("Student doesn't exist.");
        }
        io.readString("Hit enter to continue.");
        }
    
    public void displayExitBanner(){
        io.print("Good bye!");
        
    }
    
    public void displayUnknownCommandBanner(){
        io.print("UNKNOWN COMMAND! ");
        
    }
    
    public ClassRosterView(UserIO io){
        this.io = io;
    }
    
    public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
}
}
    

