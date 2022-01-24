/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.classroster.service;

import com.raveenm.classroster.dao.ClassRosterPersistenceException;
import com.raveenm.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface ClassRosterServiceLayer {
    void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;
    
    List<Student> getAllStudents() throws 
            ClassRosterPersistenceException;
    
    Student getStudent (String studentId) throws
            ClassRosterPersistenceException;
    
    Student removeStudent (String studentId) throws
            ClassRosterPersistenceException;
}
