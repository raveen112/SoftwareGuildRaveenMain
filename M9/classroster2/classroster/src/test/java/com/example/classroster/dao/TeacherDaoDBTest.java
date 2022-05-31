/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.classroster.dao;

import com.example.classroster.dto.Course;
import com.example.classroster.dto.Student;
import com.example.classroster.dto.Teacher;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ravee
 */
@SpringBootTest
public class TeacherDaoDBTest {
    
    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    CourseDao courseDao;
    
    public TeacherDaoDBTest() {
    }
    
    
    @BeforeEach
    public void setUp() {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        teachers.forEach(teacher -> {
            teacherDao.deleteTeacherById(teacher.getId());
        });
        
        List<Student> students = studentDao.getAllStudents();
        students.forEach(student -> {
            studentDao.deleteStudentById(student.getId());
        });
        
        List<Course> courses = courseDao.getAllCourses();
        courses.forEach(course -> {
            courseDao.deleteCourseById(course.getId());
        });
    }


 
    /**
     * Test of getAllTeachers method, of class TeacherDaoDB.
     */
    @Test
    public void testGetAllTeachers() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Test First 2");
        teacher2.setLastName("Test Last 2");
        teacher2.setSpecialty("Test Specialty 2");
        teacher2 = teacherDao.addTeacher(teacher2);
        
        List<Teacher> teachers = teacherDao.getAllTeachers();
        
        assertEquals(2, teachers.size());
        assertTrue(teachers.contains(teacher));
        assertTrue(teachers.contains(teacher2));
    }

    /**
     * Test of addTeacher method & getTeacherById method, of class TeacherDaoDB.
     */
    @Test
    public void testAddAndGetTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Teacher getTeacher= teacherDao.getTeacherById(teacher.getId());
        
        assertEquals(teacher, getTeacher);
    }

    /**
     * Test of updateTeacher method, of class TeacherDaoDB.
     */
    @Test
    public void testUpdateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Teacher updatedTeacher = teacherDao.getTeacherById(teacher.getId());
        assertEquals(teacher, updatedTeacher);
        
        teacher.setFirstName("New Test First");
        teacherDao.updateTeacher(teacher);
        
        assertNotEquals(teacher, updatedTeacher);
        
        updatedTeacher = teacherDao.getTeacherById(teacher.getId());
        
        assertEquals(teacher, updatedTeacher);        
    }

    /**
     * Test of deleteTeacherById method, of class TeacherDaoDB.
     */
    @Test
    public void testDeleteTeacherById() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Teacher teacherToDelete = teacherDao.getTeacherById(teacher.getId());
        assertEquals(teacher, teacherToDelete);
        
        teacherDao.deleteTeacherById(teacher.getId());
        
        teacherToDelete = teacherDao.getTeacherById(teacher.getId());
        assertNull(teacherToDelete);        
    }
    
}
