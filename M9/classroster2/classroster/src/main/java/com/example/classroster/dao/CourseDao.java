/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.classroster.dao;

import com.example.classroster.dto.Course;
import com.example.classroster.dto.Student;
import com.example.classroster.dto.Teacher;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface CourseDao {

    Course getCourseById(int id);

    List<Course> getAllCourses();

    Course addCourse(Course course);

    void updateCourse(Course course);

    void deleteCourseById(int id);
    
    
    // useful for displaying information later
    List<Course> getCoursesForTeacher(Teacher teacher);
    
    List<Course> getCoursesForStudent(Student student);
    

}
