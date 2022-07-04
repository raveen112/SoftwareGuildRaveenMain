/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.dao;

import com.example.supersightings.model.Hero;
import com.example.supersightings.model.Location;
import com.example.supersightings.model.Organization;
import com.example.supersightings.model.Sighting;
import com.example.supersightings.model.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class SightingDaoDB implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String GET_SIGHTING_BY_ID = "SELECT * FROM sightings WHERE sightingId= ?";
            Sighting sighting = jdbc.queryForObject(GET_SIGHTING_BY_ID, new SightingMapper(), id);
            sighting.setLocation(getLocationForSighting(id));
            sighting.setHero(getHeroForSighting(id));
            return sighting;
        } catch (DataAccessException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    // HELPER  -----------------------------------------------------------------------------------------------------------------------------
    @Override
    public Location getLocationForSighting(int sightingId) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM sightings st JOIN location l "
                + "ON st.locationId = l.locationId "
                + "WHERE st.sightingId= ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationDaoDB.LocationMapper(), sightingId);
    }
    @Override
    public Hero getHeroForSighting(int heroId) {
        final String SELECT_HERO_FOR_SIGHTING = "SELECT h.* FROM sightings st JOIN super_people h " 
                + "ON st.superId = h.superId "
                + "WHERE st.sightingId = ?";
        Hero hero = jdbc.queryForObject(SELECT_HERO_FOR_SIGHTING, new HeroDaoDB.HeroMapper(), heroId);
        hero.setSuperPower(getSuperPowerForHero(hero.getId()));
        hero.setOrganization(getOrganizationsForHero(hero.getId()));
        return hero;
    }

    // complete once superpower implementations are complete
    private Superpower getSuperPowerForHero(int heroId) {
        final String SELECT_SUPERPOWER_FOR_HERO = "SELECT s.* FROM super_people h JOIN super_power s "+
                "ON h.superPowerId = s.superPowerId "+
                "WHERE h.superId = ?";
        return jdbc.queryForObject(SELECT_SUPERPOWER_FOR_HERO, new SuperpowerDaoDB.SuperpowerMapper(), heroId);
    }
    
    // complete once hero implementations are complete
    private List<Organization> getOrganizationsForHero(int heroId){
         final String SELECT_ORGANIZATION_FOR_HERO = "SELECT o.* FROM super_people h "+
                 "JOIN super_people_org ho "+
                 "ON h.superId = ho.superId "+
                 "JOIN super_org o "+
                 "ON ho.orgId = o.orgId "+ 
                 "WHERE h.superId = ?";
         return jdbc.query(SELECT_ORGANIZATION_FOR_HERO, new OrganizationDaoDB.OrganizationMapper(), heroId);
    }
    
    // HELPER  -----------------------------------------------------------------------------------------------------------------------------
    @Override
    public List<Sighting> getAllSightings() {
       final String GET_ALL_SIGHTINGS = "SELECT * FROM sightings";
       List<Sighting> sightings = jdbc.query(GET_ALL_SIGHTINGS, new SightingMapper());
       for(Sighting sighting : sightings){
           sighting.setLocation(getLocationForSighting(sighting.getId()));
           sighting.setHero(getHeroForSighting(sighting.getId()));
       }
       
       return sightings;
    }

    @Transactional
    @Override
    public Sighting addSighting(Sighting sighting) {
       final String INSERT_SIGHTING = "INSERT INTO sightings(date, locationId, superId) "+
               "VALUES(?,?,?)";
       jdbc.update(INSERT_SIGHTING,
               sighting.getDate(),
               sighting.getLocation().getId(),
               sighting.getHero().getId());
       
       int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
       sighting.setId(newId);
       return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
       final String UPDATE_SIGHTINGS = "UPDATE sightings SET date = ?, locationId = ?, superId=? "+ 
               "WHERE sightingId=?";
       jdbc.update(UPDATE_SIGHTINGS, 
               sighting.getDate(),
               sighting.getLocation().getId(),
               sighting.getHero().getId(),
               sighting.getId()
       );
    }
    
    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        final String GET_SIGHTINGS_BY_DATE = "SELECT * FROM sightings WHERE date = ?";
        List<Sighting> sightings = jdbc.query(GET_SIGHTINGS_BY_DATE, new SightingMapper(), date);
        for (Sighting sighting : sightings){
            sighting.setLocation(getLocationForSighting(sighting.getId()));
            sighting.setHero(getHeroForSighting(sighting.getId()));
        }

        return sightings;
    }
    
    

    @Override
    public void deleteSightingById(int id) {
       final String DELETE_SIGHTING = "DELETE FROM sightings WHERE sightingId = ?";
        jdbc.update(DELETE_SIGHTING, id);
    }


    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setId(rs.getInt("sightingId"));

            if (rs.getString("date") != null) {
                sighting.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
            }

            return sighting;
        }

    }
}
