/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.dao;

import com.example.supersightings.model.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface SightingDao {
    
    // CRUD functionality 
    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting Sighting);
    void updateSighting(Sighting Sighting);
    void deleteSightingById(int id);
    
    
    // helper methods?
    public List<Sighting> getSightingsByDate(LocalDate date);
}
