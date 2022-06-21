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
import java.util.List;
import org.junit.jupiter.api.Assertions;
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
public class OrganizationDaoDBTest {
    
    public OrganizationDaoDBTest() {
    }
    
    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SuperpowerDao superpowerDao;

    @BeforeEach
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationById(location.getId());
        }
        List<Hero> heroes = heroDao.getAllHero();
        for (Hero hero : heroes) {
            heroDao.deleteHeroById(hero.getId());
        }
        List<Organization> organizations = organizationDao.getAllOrganization();
        for (Organization organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getId());
        }
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getId());
        }
    }
   

    /**
     * Test of getOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddAndGetOrganization() {
     
        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Earths mightiest heroes");
        organization.setAddress("Atlanta");
        organization = organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        
        assertEquals(fromDao, organization);
      }

    /**
     * Test of getAllOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganization() {
        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Earths mightiest heroes");
        organization.setAddress("Atlanta");
        organization = organizationDao.addOrganization(organization);
        
        List<Organization> organizations = organizationDao.getAllOrganization();

        assertEquals(1, organizations.size());
        assertTrue(organizations.contains(organization));
    }


    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Earths mightiest heroes");
        organization.setAddress("Atlanta");
        organization = organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        
        assertEquals(organization, fromDao);

        organization.setName("DCEU");
        organizationDao.updateOrganization(organization);
        
        Assertions.assertNotEquals(fromDao, organization);

        fromDao = organizationDao.getOrganizationById(organization.getId());
        
        assertEquals(organization, fromDao);
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationById() {
        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Earths mightiest heroes");
        organization.setAddress("Atlanta");
        organization = organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        
        assertEquals(organization, fromDao);

        organizationDao.deleteOrganizationById(organization.getId());
        
        fromDao = organizationDao.getOrganizationById(organization.getId());
        assertNull(fromDao);
    }
    
}
