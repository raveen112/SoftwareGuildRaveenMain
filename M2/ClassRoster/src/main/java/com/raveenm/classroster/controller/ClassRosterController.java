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
import com.raveenm.classroster.dao.ClassRosterDaoException;

/**
 *
 * @author ravee
 */
public class ClassRosterController {
    
    private UserIO io = new UserIOConsoleImpl();
    private ClassRosterView view ;
    private ClassRosterDao dao ;
    
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
    }catch (ClassRosterDaoException e){
        view.displayErrorMessage(e.getMessage());
    }
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent()throws ClassRosterDaoException{
        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentId(), newStudent);
        view.displayCreateSuccessBanner();
        
    }
    
    private void listStudents()throws ClassRosterDaoException{
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void viewStudent()throws ClassRosterDaoException{
        view.displayDisplayStudentBanner();
        String studentId = view.getStringIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }
    
    private void removeStudent()throws ClassRosterDaoException{
        view.displayRemovedStudentBanner();
        String studentId = view.getStringIdChoice();
        Student removedStudent = dao.removeStudent(studentId);
        view.displayRemoveStudentResult(removedStudent);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
    public ClassRosterController(ClassRosterDao dao, ClassRosterView view){
        this.dao = dao;
        this.view = view;
    }
    
    
}

