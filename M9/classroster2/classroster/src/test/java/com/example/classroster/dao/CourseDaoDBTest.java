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
public class CourseDaoDBTest {
    
    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    CourseDao courseDao;
    
    public CourseDaoDBTest() {
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
     * Test of getAllCourses method, of class CourseDaoDB.
     */
    @Test
    public void testGetAllCourses() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course1 = new Course();
        course1.setName("Test Course Name 1");
        course1.setTeacher(teacher);
        course1.setStudents(students);
        course1 = courseDao.addCourse(course1);
        
        Course course2 = new Course();
        course2.setName("Test Course Name 2");
        course2.setTeacher(teacher);
        course2.setStudents(students);
        course2 = courseDao.addCourse(course2);      
        
        List<Course> courses = courseDao.getAllCourses();
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));        
    }

    /**
     * Test of addCourse method & getCourseById method, of class CourseDaoDB.
     */
    @Test
    public void testAddAndGetCourse() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course addedCourse = courseDao.getCourseById(course.getId());
        assertEquals(course, addedCourse);        
    }

    /**
     * Test of updateCourse method, of class CourseDaoDB.
     */
    @Test
    public void testUpdateCourse() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First 1");
        student.setLastName("Test Student Last 1");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course updatedCourse = courseDao.getCourseById(course.getId());
        assertEquals(course, updatedCourse);
        
        course.setName("New Test Course Name");
        Student student2 = new Student();
        student2.setFirstName("Test Student First 2");
        student2.setLastName("Test Student Last 2");
        student2 = studentDao.addStudent(student2);
        students.add(student2);
        course.setStudents(students);
        
        courseDao.updateCourse(course);
        
        assertNotEquals(course, updatedCourse);
        
        updatedCourse = courseDao.getCourseById(course.getId());
        assertEquals(course, updatedCourse);
    }

    /**
     * Test of deleteCourseById method, of class CourseDaoDB.
     */
    @Test
    public void testDeleteCourseById() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);
        
        Course courseToBeDeleted = courseDao.getCourseById(course.getId());
        assertEquals(course, courseToBeDeleted);
        
        courseDao.deleteCourseById(course.getId());
        
        courseToBeDeleted = courseDao.getCourseById(course.getId());
        assertNull(courseToBeDeleted);
    }

    /**
     * Test of getCoursesForTeacher method, of class CourseDaoDB.
     */
    @Test
    public void testGetCoursesForTeacher() {
        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Test Teacher First 1");
        teacher1.setLastName("Test Teacher Last 1");
        teacher1.setSpecialty("Test Teacher Specialty 1");
        teacher1 = teacherDao.addTeacher(teacher1);
        
        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Test Teacher First 2");
        teacher2.setLastName("Test Teacher Last 2");
        teacher2.setSpecialty("Test Teacher Specialty 2");
        teacher2 = teacherDao.addTeacher(teacher2);
        
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        
        List<Student> students = new ArrayList<>();
        students.add(student);
        
        Course course1 = new Course();
        course1.setName("Test Course Name 1");
        course1.setTeacher(teacher1);
        course1.setStudents(students);
        course1 = courseDao.addCourse(course1);
        
        Course course2 = new Course();
        course2.setName("Test Course Name 2");
        course2.setTeacher(teacher2);
        course2.setStudents(students);
        course2 = courseDao.addCourse(course2);
        
        Course course3 = new Course();
        course3.setName("Test Course Name");
        course3.setTeacher(teacher1);
        course3.setStudents(students);
        course3 = courseDao.addCourse(course3);
        
        List<Course> courses = courseDao.getCoursesForTeacher(teacher1);   
        
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertFalse(courses.contains(course2));
        assertTrue(courses.contains(course3));
    }

    /**
     * Test of getCoursesForStudent method, of class CourseDaoDB.
     */
    @Test
    public void testGetCoursesForStudent() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpecialty("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        
        Student student1 = new Student();
        student1.setFirstName("Test Student First 1");
        student1.setLastName("Test Student Last 1");
        student1 = studentDao.addStudent(student1);
        
        Student student2 = new Student();
        student2.setFirstName("Test Student First 2");
        student2.setLastName("Test Student Last 2");
        student2 = studentDao.addStudent(student2);
        
        List<Student> students1 = new ArrayList<>();
        students1.add(student1);
        students1.add(student2);
        
        List<Student> students2 = new ArrayList<>();
        students2.add(student2);
        
        Course course1 = new Course();
        course1.setName("Test Course Name 1");
        course1.setTeacher(teacher);
        course1.setStudents(students1);
        course1 = courseDao.addCourse(course1);
        
        Course course2 = new Course();
        course2.setName("Test Course Name 2");
        course2.setTeacher(teacher);
        course2.setStudents(students2);
        course2 = courseDao.addCourse(course2);
        
        Course course3 = new Course();
        course3.setName("Test Course Name 3");
        course3.setTeacher(teacher);
        course3.setStudents(students1);
        course3 = courseDao.addCourse(course3);
        
        List<Course> courses = courseDao.getCoursesForStudent(student1);
        
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertFalse(courses.contains(course2));
        assertTrue(courses.contains(course3));       
    }
    
}
