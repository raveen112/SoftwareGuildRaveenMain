/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.dao;

import com.example.supersightings.model.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ravee
 */
@Repository
public class SuperpowerDaoDB implements SuperpowerDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superpower getSuperpowerById(int id) {
       try{
       final String GET_SUPERPOWER_BY_ID = "SELECT * FROM super_power WHERE superPowerId = ?";
       return jdbc.queryForObject(GET_SUPERPOWER_BY_ID, new SuperpowerMapper(), id);
       } catch(DataAccessException ex){
           return null;
       }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String GET_ALL_SUPERPOWERS = "SELECT * FROM super_power";
        return jdbc.query(GET_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    public Superpower addSuperpower(Superpower superpower) {
        final String INSERT_SUPERPOWER = "INSERT INTO super_power(superPowerName) "+
                "VALUES(?)";
        jdbc.update(INSERT_SUPERPOWER, superpower.getName());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setId(newId);
        return superpower;
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE super_power SET superPowerName = ? WHERE superPowerId =?";
        jdbc.update(UPDATE_SUPERPOWER, superpower.getName(), superpower.getId());
    }

    @Override
    public void deleteSuperpowerById(int id) {
      final String DELETE_HERO_FROM_ORGANIZATION = "DELETE ho.* FROM super_people_org ho "+
              "JOIN super_people h " +
              "ON ho.superId = h.superId "+
              "JOIN super_power s "+
              "ON s.superPowerId = h.superPowerId " + 
              "WHERE s.superPowerId = ?";
      

      jdbc.update(DELETE_HERO_FROM_ORGANIZATION, id);
      
      final String DELETE_HERO_FROM_SIGHTING = "DELETE st.* FROM sightings st " +  
              "JOIN super_people h " +
              "ON st.superId = h.superId " +
              "WHERE h.superPowerId = ?";
      jdbc.update(DELETE_HERO_FROM_SIGHTING, id);
      
      final String DELETE_HERO = "DELETE FROM super_people WHERE superPowerId =?";
      jdbc.update(DELETE_HERO, id);
      
      final String DELETE_SUPERPOWER = "DELETE FROM super_power WHERE superPowerId = ?";
      jdbc.update(DELETE_SUPERPOWER, id);
      
    }
    
    public static final class SuperpowerMapper implements RowMapper<Superpower>{

        @Override
        public Superpower mapRow(ResultSet rs, int rowNum) throws SQLException {
           Superpower superpower = new Superpower();
           superpower.setId(rs.getInt("superPowerId"));
           superpower.setName(rs.getString("superPowerName"));
           return superpower;
        }
        
    }
}
