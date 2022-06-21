xxxxxxxxxxxxxxx/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.hotelreservation.dao;

import com.raveenm.hotelreservation.model.Guest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ravee
 */
public class HotelReservationGuestDBImpl implements HotelReservationGuestDB{
    
    JdbcTemplate jdbc;

    @Transactional
    @Override
    public Guest addGuest() {
        final String sql = "INSERT INTO guest(first_name, last_name, address, city, state, zip, contact_number) VALUES(?, ?, ?, ?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

        
            return statement;

        }, keyHolder);

        game.setGame_id(keyHolder.getKey().intValue());
        return game;
    }

    @Override
    public List<Guest> getAllGuests() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Guest removeGuest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Guest updateGuest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

