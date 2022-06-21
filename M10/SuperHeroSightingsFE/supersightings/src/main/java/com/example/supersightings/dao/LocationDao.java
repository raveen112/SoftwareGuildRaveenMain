/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.dao;

import com.example.supersightings.model.Hero;
import com.example.supersightings.model.Location;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface LocationDao {

    Location getLocationById(int id);

    List<Location> getAllLocations();

    Location addLocation(Location location);

    void updateLocation(Location location);

    void deleteLocationById(int id);
    
    // helper method to get all the supes for location
    List<Location> getAllLocationsForHero(Hero hero);
}
