/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ravee
 */
public class FlooringMasteryProductDaoFileImplTest {
    
    FlooringMasteryProductDao testDao;
    
    public FlooringMasteryProductDaoFileImplTest() {
    }
    
    
    @BeforeEach
    public void setUp() {
        // add file to directory
        this.testDao = new FlooringMasteryProductDaoFileImpl("Tests/FlooringMasteryProductDao/Products.txt");
    }
    

    @Test
    public void testSomeMethod() {
        fail("The test case is a prototype.");
    }
    
}
