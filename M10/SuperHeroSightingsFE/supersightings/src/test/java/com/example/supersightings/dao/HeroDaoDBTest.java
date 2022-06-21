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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ravee
 */
@SpringBootTest
public class HeroDaoDBTest {

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

    public HeroDaoDBTest() {
    }

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
     * Test of getHeroById method, of class HeroDaoDB.
     */
    @Test
    public void testAddAndGetHero() {
        Superpower superpower = new Superpower();
        superpower.setName("Powerman");
        superpower = superpowerDao.addSuperpower(superpower);

        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Earths mightiest heroes");
        organization.setAddress("Atlanta");
        organization = organizationDao.addOrganization(organization);
        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setName("String-man");
        hero.setDescription("Long and stringy");
        hero.setOrganization(organizations);
        hero.setSuperPower(superpower);
        hero = heroDao.addHero(hero);

        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(fromDao, hero);
    }

    /**
     * Test of getAllHero method, of class HeroDaoDB.
     */
    @Test
    public void testGetAllHero() {
        Superpower superpower = new Superpower();
        superpower.setName("Powerman");
        superpower = superpowerDao.addSuperpower(superpower);

        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Earths mightiest heroes");
        organization.setAddress("Atlanta");
        organization = organizationDao.addOrganization(organization);
        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setName("String-man");
        hero.setDescription("Long and stringy");
        hero.setOrganization(organizations);
        hero.setSuperPower(superpower);
        hero = heroDao.addHero(hero);

        List<Hero> heroes = heroDao.getAllHero();

        assertEquals(1, heroes.size());
        assertTrue(heroes.contains(hero));

    }

    /**
     * Test of updateHero method, of class HeroDaoDB.
     */
    @Test
    public void testUpdateHero() {
        Superpower superpower = new Superpower();
        superpower.setName("Powerman");
        superpower = superpowerDao.addSuperpower(superpower);

        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Earths mightiest heroes");
        organization.setAddress("Atlanta");
        organization = organizationDao.addOrganization(organization);
        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setName("String-man");
        hero.setDescription("Long and stringy");
        hero.setOrganization(organizations);
        hero.setSuperPower(superpower);
        hero = heroDao.addHero(hero);

        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);

        hero.setName("AngloMan");
        heroDao.updateHero(hero);
        Assertions.assertNotEquals(fromDao, hero);

        fromDao = heroDao.getHeroById(hero.getId());

        assertEquals(hero, fromDao);

    }

    /**
     * Test of deleteHeroById method, of class HeroDaoDB.
     */
    @Test
    public void testDeleteHeroById() {
        
        Superpower superpower = new Superpower();
        superpower.setName("Powerman");
        superpower = superpowerDao.addSuperpower(superpower);

        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Earths mightiest heroes");
        organization.setAddress("Atlanta");
        organization = organizationDao.addOrganization(organization);
        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setName("String-man");
        hero.setDescription("Long and stringy");
        hero.setOrganization(organizations);
        hero.setSuperPower(superpower);
        hero = heroDao.addHero(hero);
        
        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);
        
        heroDao.deleteHeroById(hero.getId());
        
        fromDao = heroDao.getHeroById(hero.getId());

        assertNull(fromDao);
    }

    /**
     * Test of getAllHeroSightedAtLocation method, of class HeroDaoDB.
     */
    @Test
    public void testGetAllHeroSightedAtLocation() {
        Superpower superpower = new Superpower();
        superpower.setName("Powerman");
        superpower = superpowerDao.addSuperpower(superpower);

        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("Earths mightiest heroes");
        organization.setAddress("Atlanta");
        organization = organizationDao.addOrganization(organization);
        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        Location location = new Location();
        location.setName("Toronto");
        location.setDescription("6ix");
        location.setAddress("ON, CA");
        location.setLatitude("11.11");
        location.setLongitude("-11.23");
        locationDao.addLocation(location);

        Hero hero = new Hero();
        hero.setName("String-man");
        hero.setDescription("Long and stringy");
        hero.setOrganization(organizations);
        hero.setSuperPower(superpower);
        hero = heroDao.addHero(hero);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHero(hero);
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        List<Hero> heroesSightedAtLocation = heroDao.getAllHeroSightedAtLocation(location);
        assertTrue(heroesSightedAtLocation.contains(hero));
    }

    /**
     * Test of getMembersForOrganization method, of class HeroDaoDB.
     */
}
