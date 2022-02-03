/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Product;
import com.raveenm.flooringmastery.dto.Tax;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
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
public class FlooringMasteryTaxDaoFileImplTest {

    FlooringMasteryTaxDao testDao;

    public FlooringMasteryTaxDaoFileImplTest() {
        this.testDao = new FlooringMasteryTaxDaoFileImpl("Tests/FlooringMasteryTaxDao/Tax.txt");
    }

    @BeforeEach
    public void setUp() throws IOException {
        PrintWriter out;
        out = new PrintWriter(new FileWriter("Tests/FlooringMasteryTaxDao/Tax.txt"));
        out.println("State,StateName,TaxRate\n"
                + "TX,Texas,4.45\n"
                + "WA,Washington,9.25\n"
                + "KY,Kentucky,6.00\n"
                + "CA,California,25.00");

        out.flush();
        out.close();
    }

    @Test
    public void testGetAllStateTaxes() throws OrderPersistenceException {
        Tax taxTexas = new Tax("TX", "Texas", new BigDecimal("4.45").setScale(2, RoundingMode.HALF_UP));
        Tax taxWashington = new Tax("WA", "Washington", new BigDecimal("9.25").setScale(2, RoundingMode.HALF_UP));
        Tax taxKentucky = new Tax("KY", "Kentucky", new BigDecimal("6.00").setScale(2, RoundingMode.HALF_UP));
        Tax taxCalifornia = new Tax("CA", "California", new BigDecimal("25.00").setScale(2, RoundingMode.HALF_UP));

        List<Tax> getAllStateTaxes = testDao.getAllStateTaxes();

        assertEquals(getAllStateTaxes.size(), 4, "Tax file should contain 4 states.");
        assertTrue(getAllStateTaxes.contains(taxTexas), "Tax file should contain Texas");
        assertTrue(getAllStateTaxes.contains(taxWashington), "Tax file should contain Washington");
        assertTrue(getAllStateTaxes.contains(taxKentucky), "Tax file should contain Kentucky");
        assertTrue(getAllStateTaxes.contains(taxCalifornia), "Tax file should contain California");
    }

}
