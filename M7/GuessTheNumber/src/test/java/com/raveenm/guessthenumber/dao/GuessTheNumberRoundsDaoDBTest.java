/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ravee
 */
@SpringBootTest
public class GuessTheNumberRoundsDaoDBTest {
    
    @Autowired
    GuessTheNumberGameDao gameDao;

    @Autowired
    GuessTheNumberRoundsDao roundDao;
    
    public GuessTheNumberRoundsDaoDBTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addRound method, of class GuessTheNumberRoundsDaoDB.
     */
    @Test
    public void testAddRound() {
    }

    /**
     * Test of getAllRounds method, of class GuessTheNumberRoundsDaoDB.
     */
    @Test
    public void testGetAllRounds() {
    }

    /**
     * Test of getRoundById method, of class GuessTheNumberRoundsDaoDB.
     */
    @Test
    public void testGetRoundById() {
    }

    /**
     * Test of updateRoundById method, of class GuessTheNumberRoundsDaoDB.
     */
    @Test
    public void testUpdateRoundById() {
    }

    /**
     * Test of deleteRoundById method, of class GuessTheNumberRoundsDaoDB.
     */
    @Test
    public void testDeleteRoundById() {
    }
    
}
