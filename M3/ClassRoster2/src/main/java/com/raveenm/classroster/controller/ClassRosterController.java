/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.classroster.controller;

import com.raveenm.classroster.dao.ClassRosterDaoFileImpl;
import com.raveenm.classroster.dto.Student;
import com.raveenm.classroster.ui.ClassRosterView;
import com.raveenm.classroster.ui.UserIO;
import com.raveenm.classroster.ui.UserIOConsoleImpl;
import java.util.List;
import com.raveenm.classroster.dao.ClassRosterDao;
import com.raveenm.classroster.dao.ClassRosterPersistenceException;
import com.raveenm.classroster.service.ClassRosterDataValidationException;
import com.raveenm.classroster.service.ClassRosterDuplicateIdException;
import com.raveenm.classroster.service.ClassRosterServiceLayer;

/**
 *
 * @author ravee
 */
public class ClassRosterController {
    
    private UserIO io = new UserIOConsoleImpl();
    private ClassRosterView view ;
    private ClassRosterServiceLayer service;
    
    public void run(){
        
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
        while(keepGoing){
            
            menuSelection = getMenuSelection();
           
            
            switch(menuSelection){
                case 1:
                    listStudents();
                    break;
                    
                case 2:
                    createStudent();
                    break;
                    
                case 3:
                    viewStudent();
                    break;
                   
                case 4:
                    removeStudent();
                    break;
                    
                case 5:
                   keepGoing = false;
                   break;
                    
                default:
                    unknownCommand();
            }
        }
            exitMessage();
    }catch (ClassRosterPersistenceException e){
        view.displayErrorMessage(e.getMessage());
    }
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent()throws ClassRosterPersistenceException{
        view.displayCreateStudentBanner();
        
        boolean hasErrors = false;
        do{
        Student currentStudent = view.getNewStudentInfo();
        try{
        service.createStudent(currentStudent);
        view.displayCreateSuccessBanner();
        hasErrors = false;
        
        }catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e){
            hasErrors= true;
            view.displayErrorMessage(e.getMessage());
        }
        
         }while (hasErrors);
        }
    
    private void listStudents()throws ClassRosterPersistenceException{
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void viewStudent()throws ClassRosterPersistenceException{
        view.displayDisplayStudentBanner();
        String studentId = view.getStringIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }
    
    private void removeStudent()throws ClassRosterPersistenceException{
        view.displayRemovedStudentBanner();
        String studentId = view.getStringIdChoice();
        Student removedStudent = service.removeStudent(studentId);
        view.displayRemoveStudentResult(removedStudent);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
   public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
    this.service = service;
    this.view = view;
}
    
    
}

