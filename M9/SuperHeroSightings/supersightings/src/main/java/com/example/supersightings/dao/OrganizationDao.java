/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.dao;

import com.example.supersightings.model.Hero;
import com.example.supersightings.model.Organization;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface OrganizationDao {
    // CRUD functionality 
    Organization getOrganizationById(int id);
    List<Organization> getAllOrganization();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationById(int id);
    
    
    List<Organization> getOrganizationByHero(Hero hero);
}
