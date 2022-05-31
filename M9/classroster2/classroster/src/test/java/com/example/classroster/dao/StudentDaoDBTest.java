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
public class StudentDaoDBTest {
    
    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    CourseDao courseDao;
    
    public StudentDaoDBTest() {
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
     * Test of getAllStudents method, of class StudentDaoDB.
     */
    @Test
    public void testGetAllStudents() {
        Student student1 = new Student();
        student1.setFirstName("Test Student First");
        student1.setLastName("Test Student Last");
        student1 = studentDao.addStudent(student1);
        
        Student student2 = new Student();
        student2.setFirstName("Test Student First 2");
        student2.setLastName("Test Student Last 2");
        student2 = studentDao.addStudent(student2);
        
        List<Student> students = studentDao.getAllStudents();
        
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
    }

    /**
     * Test of addStudent method & getStudentById method, of class StudentDaoDB.
     */
    @Test
    public void testAddAndGetStudent() {
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        Student addedStudent = studentDao.getStudentById(student.getId());
        assertEquals(student, addedStudent);
    }

    /**
     * Test of updateStudent method, of class StudentDaoDB.
     */
    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        Student updatedStudent = studentDao.getStudentById(student.getId());
        assertEquals(student, updatedStudent);
        
        student.setFirstName("New Test Student First");
        studentDao.updateStudent(student);
        
        assertNotEquals(student, updatedStudent);
        
        updatedStudent = studentDao.getStudentById(student.getId());
        
        assertEquals(student, updatedStudent);        
    }

    /**
     * Test of deleteStudentById method, of class StudentDaoDB.
     */
    @Test
    public void testDeleteStudentById() {
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
        
        Student studentToDelete = studentDao.getStudentById(student.getId());
        assertEquals(student, studentToDelete);
        
        studentDao.deleteStudentById(student.getId());
        
        studentToDelete = studentDao.getStudentById(student.getId());
        assertNull(studentToDelete);      
    }
    
}
