/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.dao;

import com.example.supersightings.model.Hero;
import com.example.supersightings.model.Location;
import com.example.supersightings.model.Organization;
import com.example.supersightings.model.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ravee
 */
public class HeroDaoDB implements HeroDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hero getHeroById(int id) {
        try {
            final String GET_HERO_BY_ID = "SELECT * super_people WHERE superId = ?";
            Hero hero = jdbc.queryForObject(GET_HERO_BY_ID, new HeroMapper(), id);
            hero.setSuperPower(getSuperPowerForHero(id));
            hero.setOrganization(getOrganizationForHero(id));
            return hero;
        } catch (DataAccessException ex) {
            return null;
        }

    }

    // HELPER--------------------------------------------------------------------------------------------------------------------
    private Superpower getSuperPowerForHero(int heroId) {
        final String SELECT_SUPERPOWER_FOR_HERO = "SELECT s.* FROM super_people h JOIN super_power s "
                + "ON h.superPowerId = s.superPowerId "
                + "WHERE h.heroId =?";
        return jdbc.queryForObject(SELECT_SUPERPOWER_FOR_HERO, new SuperpowerDaoDB.SuperpowerMapper(), heroId);
    }

    private List<Organization> getOrganizationForHero(int heroId) {
        final String SELECT_ORGANIZATION_FOR_HERO = "SELECT o* FROM super_people h "
                + "JOIN super_people_org ho "
                + "JOIN super_org o "
                + "ON ho.orgId = o.orgId "
                + "WHERE h.heroId = ?";

        return jdbc.query(SELECT_ORGANIZATION_FOR_HERO, new OrganizationDaoDB.OrganizationMapper(), heroId);

    }

    // bridge table handler
    private void insertHeroOrganization(Hero hero) {
        final String INSERT_HERO_ORGANIZATION = "INSERT INTO super_people_org(heroId, orgId) VALUES (?,?)";
        for (Organization organization : hero.getOrganizations()) {
            jdbc.update(INSERT_HERO_ORGANIZATION, hero.getId(), organization.getId());
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------
    @Override
    public List<Hero> getAllHero() {
        final String SELECT_ALL_HEROS = "SELECT * FROM super_people";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROS, new HeroMapper());

        for (Hero hero : heroes) {
            hero.setOrganization(getOrganizationForHero(hero.getId()));
            hero.setSuperPower(getSuperPowerForHero(hero.getId()));;
        }
        return heroes;
    }

    @Override
    @Transactional
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO super_people(heroName, heroDescription, superPowerId) "
                + "VALUES(?,?,?)";

        jdbc.update(INSERT_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperPower());

        int newId = jdbc.queryForObject("SELECT_LAST_INSERT_ID()", Integer.class);
        hero.setId(newId);
        insertHeroOrganization(hero);
        return hero;
    }

    @Override
    @Transactional
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE super_people SET heroName = ?, heroDescription = ?, "
                + "superPowerId = ? WHERE heroId = ?";
        jdbc.update(UPDATE_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperPower().getId(),
                hero.getId());

        final String DELETE_HERO_ORGANIZATION = "DELETE FROM super_org WHERE heroId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, hero.getId());
        insertHeroOrganization(hero);

    }

    @Override
    public void deleteHeroById(int id) {
        final String DELETE_HERO_ORG = "DELETE FROM super_org WHERE heroId=?";
        jdbc.update(DELETE_HERO_ORG, id);

        final String DELETE_HERO_SIGHTING = "SELECT FROM sighting WHERE heroId = ?";
        jdbc.update(DELETE_HERO_SIGHTING, id);

        final String DELETE_HERO = "SELECT heroId FROM super_people WHERE heroId =?";
        jdbc.update(DELETE_HERO, id);
    }

    @Override
    public List<Hero> getAllHeroSightedAtLocation(Location location) {
        final String GET_ALL_HEROS_SIGHTED_AT_LOCATION = "SELECT DISTINCT h.* "+
                "FROM super_people h "+
                "JOIN sighting st" +
                "ON h.heroId = st.heroId "+
                "WHERE heroId =?";
        
        List<Hero> heroes = jdbc.query(GET_ALL_HEROS_SIGHTED_AT_LOCATION, new HeroMapper(), location.getId());
        for(Hero hero : heroes){
            hero.setOrganization(getOrganizationForHero(hero.getId()));
            hero.setSuperPower(getSuperPowerForHero(hero.getId()));
        }
        
        return heroes;
    }

    @Override
    public List<Hero> getMembersForOrganization(Organization organization) {
       final String GET_MEMBERS_FOR_ORGANIZATION = "SELECT DISTINCT h. * FROM super_people "+
               "JOIN super_people_org ho "+
               "ON h.heroId = ho.heroId "+
               "WHERE ho.orgId = ?";
       return jdbc.query(GET_MEMBERS_FOR_ORGANIZATION, new HeroMapper(), organization.getId());
    }

    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("superId"));
            hero.setName(rs.getString("heroName"));
            hero.setDescription(rs.getString("heroDescription"));
            return hero;
        }

    }

}
