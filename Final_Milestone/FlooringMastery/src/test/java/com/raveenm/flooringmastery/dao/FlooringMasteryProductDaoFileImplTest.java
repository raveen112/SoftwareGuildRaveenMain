/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ravee
 */
public class FlooringMasteryProductDaoFileImplTest {

    FlooringMasteryProductDao testDao;

    public FlooringMasteryProductDaoFileImplTest() {
        this.testDao = new FlooringMasteryProductDaoFileImpl("Tests/FlooringMasteryProductDao/Products.txt");
    }

    @BeforeAll
    public static void setUp() throws IOException {
        // add file to directory
        PrintWriter out;
        out = new PrintWriter(new FileWriter("Tests/FlooringMasteryProductDao/Products.txt"));
        out.println("ProductType,CostPerSquareFoot,LaborCostPerSquareFoot\n"
                + "Carpet,2.25,2.10\n"
                + "Laminate,1.75,2.10\n"
                + "Tile,3.50,4.15\n"
                + "Wood,5.15,4.75");

        out.flush();
        out.close();

    }

    @Test
    public void testGetAllProducts() throws OrderPersistenceException {
        Product productCarpet = new Product("Carpet", new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP), new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        Product productLaminate = new Product("Laminate", new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP), new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        Product productTile = new Product("Tile", new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP), new BigDecimal("4.15").setScale(2, RoundingMode.HALF_UP));
        Product productWood = new Product("Wood", new BigDecimal("5.15").setScale(2, RoundingMode.HALF_UP), new BigDecimal("4.75").setScale(2, RoundingMode.HALF_UP));

        List<Product> getAllProducts = testDao.getallProductTypes();

        assertEquals(getAllProducts.size(), 4, "Product file should contain 4 products.");
        assertTrue(getAllProducts.contains(productCarpet), "Product file should contain Carpet");
        assertTrue(getAllProducts.contains(productLaminate), "Product file should contain Laminate");
        assertTrue(getAllProducts.contains(productTile), "Product file should contain Tile");
        assertTrue(getAllProducts.contains(productWood), "Product file should contain Wood");

    }

}
