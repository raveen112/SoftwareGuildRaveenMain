/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.VoiceOfTOM.dao;

import com.example.VoiceOfTOM.model.Associate;
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
public class AssociateDaoImpl implements AssociateDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Associate getAssociateById(int id) {
        try {
            final String GET_Associate_BY_ID = "SELECT * FROM associate WHERE id= ?";
            Associate Associate = jdbc.queryForObject(GET_Associate_BY_ID, new AssociateMapper(), id);
            return Associate;
        } catch (DataAccessException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    // Helper methods
    @Override
    public Associate getAssociateByLogin(String login){
        try{
            final String GET_ASSOCIATE_BY_LOGIN = "SELECT * FROM associate WHERE login= ?";
            Associate associate = jdbc.queryForObject(GET_ASSOCIATE_BY_LOGIN, new AssociateMapper(), login);
            return associate;
        } catch(DataAccessException ex){
            System.out.println(ex.getMessage());
            return null;
        }
        
    }
    
    @Override
    public List<Associate> getAllAssociates() {
        final String GET_ALL_ASSOCIATES = "SELECT * FROM associate";
        return jdbc.query(GET_ALL_ASSOCIATES, new AssociateMapper());
    }

    @Override
    @Transactional
    public Associate addAssociate(Associate Associate) {
        final String INSERT_Associate = "INSERT INTO associate(name, login) VALUES(?, ?);";

        jdbc.update(INSERT_Associate,
                Associate.getName(),
                Associate.getLogin()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Associate.setAssociate_id(newId);
        return Associate;

    }

    @Override
    public void updateAssociate(Associate Associate) {
        final String UPDATE_Associate = "UPDATE associate SET name=?, login=?  WHERE id =?";
        jdbc.update(UPDATE_Associate,
                Associate.getName(),
                Associate.getName(),
                Associate.getLogin(),
                Associate.getAssociate_id());
    }

    @Override
    public void deleteAssociateById(int id) {
        final String DELETE_Associate = "DELETE FROM associate WHERE id=?";
        jdbc.update(DELETE_Associate, id);
    }

    public static final class AssociateMapper implements RowMapper<Associate> {

        @Override
        public Associate mapRow(ResultSet rs, int index) throws SQLException {
            Associate Associate = new Associate();
            Associate.setAssociate_id(rs.getInt("id"));
            Associate.setName(rs.getString("name"));
            Associate.setLogin(rs.getString("login"));

            return Associate;
        }
    }

}
