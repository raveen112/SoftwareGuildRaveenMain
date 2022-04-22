/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 *
 * @author ravee
 */
@SpringBootTest
public class GuessTheNumberServiceTest {

    @Autowired
    GuessTheNumberService service;
    
   

    /**
     * Test of calculateResult method, of class GuessTheNumberService.
     */
    @Test
    public void testCalculateResult() {
        String res = service.calculateResult("1234", "4321");
      
        assertEquals("e:0 p:4", res);
    }

    
    
}
