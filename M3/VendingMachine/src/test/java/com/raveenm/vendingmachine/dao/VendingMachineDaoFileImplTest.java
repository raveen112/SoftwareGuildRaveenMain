/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.dao;

import com.raveenm.vendingmachine.dto.Inventory;
import java.io.PrintWriter;
import org.junit.jupiter.api.BeforeEach;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ravee
 */
public class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;

    Inventory item1 = new Inventory("1001", "Dr.Pepper", new BigDecimal("1.25"), 1);
    Inventory item2 = new Inventory("1002", "Coke", new BigDecimal("1.25"), 5);
    Inventory item3 = new Inventory("1003", "Koolaid", new BigDecimal("0.50"), 4);

    //  Inventory 
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testStock.txt";
        PrintWriter out = new PrintWriter(testFile);
        out.println("1001::Dr.Pepper::1.25::1\n"
                + "1002::Coke::1.25::5\n"
                + "1003::Koolaid::0.50::4");

        out.flush();
        out.close();
        testDao = new VendingMachineDaoFileImpl(testFile);
        // If all tests pass then this explicitly tests the load method
        //testDao.readInventory();

    }

    @Test
    void testGetAllItems() throws VendingMachineDaoException {
        List<Inventory> allItems = testDao.getAllItems();

        assertNotNull(allItems);
        assertEquals(3, allItems.size());
        assertTrue(allItems.contains(item1));
        assertTrue(allItems.contains(item2));
        assertTrue(allItems.contains(item3));
    }

    @Test
    void testGetSingleItem() throws VendingMachineDaoException {
        Inventory testItem1 = testDao.getSingleItem("1001");
        assertEquals(item1, testItem1);

        Inventory testItem2 = testDao.getSingleItem("1002");
        assertEquals(item2, testItem2);

        Inventory testItem3 = testDao.getSingleItem("1003");
        assertEquals(item3, testItem3);

    }

    @Test
    void testGetStock() {
        int stock1 = testDao.getStock(item1);
        int stock2 = testDao.getStock(item2);
        int stock3 = testDao.getStock(item3);

        assertEquals(1, stock1);
        assertEquals(5, stock2);
        assertEquals(4, stock3);
    }
    
    //CRUD - Update method test
    @Test
    void testDispenseItem() throws VendingMachineDaoException{
        
        int stock1= testDao.getStock(item1);
        Inventory updateStock = testDao.dispenseItem(item1.getId());
        assertEquals(stock1-1, updateStock.getItemCount());
        
        
    }

}
