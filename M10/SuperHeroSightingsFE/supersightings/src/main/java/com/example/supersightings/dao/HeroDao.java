/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.dao;

import com.example.supersightings.model.Hero;
import com.example.supersightings.model.Location;
import com.example.supersightings.model.Organization;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface HeroDao {

    Hero getHeroById(int id);

    List<Hero> getAllHero();

    Hero addHero(Hero hero);

    void updateHero(Hero hero);

    void deleteHeroById(int id);

    List<Hero> getMembersForOrganization(Organization organization);

    List<Hero> getAllHeroSightedAtLocation(Location location);
}
