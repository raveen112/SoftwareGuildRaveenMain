/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface FlooringMasteryTaxDao {
    
    
    Tax getStateTax(String stateAbbreviation) throws OrderPersistenceException;
    
    
    List<Tax> getAllStateTaxes() throws OrderPersistenceException;
}
