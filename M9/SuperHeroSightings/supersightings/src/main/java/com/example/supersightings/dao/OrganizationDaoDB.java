/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.dao;

import com.example.supersightings.model.Hero;
import com.example.supersightings.model.Organization;
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
public class OrganizationDaoDB implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationById(int id) {
        try {
            final String GET_ORGANIZATION_BY_ID = "SELECT * FROM super_org WHERE orgId = ?";
            Organization organization = jdbc.queryForObject(GET_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
            List<Hero> members = getMembersForOrganization(organization);
            organization.setMembers(members);
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    // HELPER ------------------------------------------------------------------------------------------------------------
    private List<Hero> getMembersForOrganization(Organization organization) {
        final String GET_MEMBERS_FOR_ORGANIZATION = "SELECT DISTINCT h.* FROM super_people h "
                + "JOIN super_people_org ho "
                + "ON h.superId = ho.superId "
                + "WHERE orgId = ?";

        return jdbc.query(GET_MEMBERS_FOR_ORGANIZATION, new HeroDaoDB.HeroMapper(), organization.getId());
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    @Override
    public List<Organization> getAllOrganization() {
        final String GET_ALL_ORGANIZATIONS = "SELECT * FROM super_org";
        return jdbc.query(GET_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    public Organization addOrganization(Organization organization) {
      final String INSERT_ORGANIZATION = "INSERT INTO super_org(orgName, orgDescription, address) "+
              "VALUES(?,?,?)";
      jdbc.update(INSERT_ORGANIZATION, 
              organization.getName(),
              organization.getDescription(),
              organization.getAddress());
      
      int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
      organization.setId(newId);
      return organization;
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE super_org SET orgName = ?, orgDescription = ?, address= ? " +
                "WHERE orgId =?";
        
        jdbc.update(UPDATE_ORGANIZATION, 
                organization.getName(),
                organization.getId());
    }

    @Override
    public void deleteOrganizationById(int id) {
    
        final String DELETE_HERO_ORGANIZATION = "DELETE ho.* "+
                "FROM super_people_org ho "+
                "JOIN super_people h " +
                "ON ho.superId = h.superId "+
                "JOIN super_org s "+
                "ON ho.orgId = s.orgId "+
                "WHERE s.orgId  = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, id);
        
        final String DELETE_ORGANIZATION = "DELETE FROM super_org WHERE orgId = ?";
        jdbc.update(DELETE_ORGANIZATION, id);
    }

    @Override
    public List<Organization> getOrganizationByHero(Hero hero) {
        return hero.getOrganizations();
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("orgId"));
            organization.setName(rs.getString("orgName"));
            organization.setDescription(rs.getString("orgDescription"));
            organization.setAddress(rs.getString("address"));
            return organization;
        }

    }

}
