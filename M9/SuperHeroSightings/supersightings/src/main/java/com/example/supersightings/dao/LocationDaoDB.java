/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.dao;

import com.example.supersightings.model.Hero;
import com.example.supersightings.model.Location;
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
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationById(int id) {
        try {
            final String GET_LOCATION_BY_ID = "SELECT * FROM location WHERE locationId = ?";

            return jdbc.queryForObject(GET_LOCATION_BY_ID, new LocationMapper(), id);

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String GET_ALL_LOCATIONS = "SELECT * FROM location";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper());

    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location(name, description, address, longitude, latitude) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLongitude(),
                location.getLatitude());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        return location;

    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET name =?, description =?, address=?, longitude=?, latitude=? "+
                "WHERE locationId =?";
        
        jdbc.update(UPDATE_LOCATION, 
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLongitude(),
                location.getLatitude(),
                location.getId());
    }

    @Override
    @Transactional
    public void deleteLocationById(int id) {
        final String DELETE_SIGHTING_LOCATION = "DELETE FROM sightings WHERE locationId = ?" ;
        jdbc.update(DELETE_SIGHTING_LOCATION, id);
        
        final String DELETE_LOCATION = "DELETE FROM location WHERE locationId = ? ";
        jdbc.update(DELETE_LOCATION, id);
    }

    @Override
    public List<Location> getAllLocationsForHero(Hero hero) {
       final String GET_ALL_LOCATION_FOR_HERO= "SELECT l.* FROM location l JOIN sighting st ON st.locationId = l.locationId WHERE st.heroid =?";
       return jdbc.query(GET_ALL_LOCATION_FOR_HERO, new LocationMapper(), hero.getId());
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("locationId"));
            location.setName(rs.getString("name"));
            location.setAddress(rs.getString("address"));
            location.setDescription(rs.getString("description"));
            location.setLatitude(rs.getString("latitude"));
            location.setLongitude(rs.getString("longitude"));

            return location;
        }

    }
}
