/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.classroster.dao;

import com.example.classroster.dto.Teacher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ravee
 */
@Repository
public class TeacherDaoDB implements TeacherDao {
  @Autowired
    JdbcTemplate jdbc;
    
    final String SELECT_TEACHER_BY_ID = "SELECT id, firstName, lastName, specialty FROM teacher WHERE id = ?";
    final String SELECT_ALL_TEACHERS = "SELECT id, firstName, lastName, specialty FROM teacher";
    final String INSERT_TEACHER = "INSERT INTO teacher(firstName, lastName, specialty) VALUES(?,?,?)";
    final String SELECT_LAST_ID = "SELECT LAST_INSERT_ID()";
    final String UPDATE_TEACHER = "UPDATE teacher SET firstName = ?, lastName = ?, specialty = ? WHERE id = ?";
    final String DELETE_COURSE_STUDENT = "DELETE cs.* FROM course_student cs JOIN course c ON cs.courseId = c.Id WHERE c.teacherId = ?";
    final String DELETE_COURSE = "DELETE FROM course WHERE teacherId = ?";
    final String DELETE_TEACHER = "DELETE FROM teacher WHERE id = ?";

    @Override
    public Teacher getTeacherById(int id) {
        try{
            return jdbc.queryForObject(SELECT_TEACHER_BY_ID, new TeacherMapper(), id);
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    @Transactional
    public List<Teacher> getAllTeachers() {
        return jdbc.query(SELECT_ALL_TEACHERS, new TeacherMapper());    
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        jdbc.update(INSERT_TEACHER, 
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getSpecialty());
        int newId = jdbc.queryForObject(SELECT_LAST_ID, Integer.class);
        teacher.setId(newId);
        
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        jdbc.update(UPDATE_TEACHER, 
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getSpecialty(),
                teacher.getId());
    }

    @Override
    @Transactional
    public void deleteTeacherById(int id) {
        jdbc.update(DELETE_COURSE_STUDENT, id);
        jdbc.update(DELETE_COURSE, id);
        jdbc.update(DELETE_TEACHER, id);
    }
    

    public static final class TeacherMapper implements RowMapper<Teacher>{
        @Override
        public Teacher mapRow(ResultSet rs, int index) throws SQLException {
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("id"));
            teacher.setFirstName(rs.getString("firstName"));
            teacher.setLastName(rs.getString("lastName"));
            teacher.setSpecialty(rs.getString("specialty"));
            
            return teacher;
        }
        
    }

    
    
}
