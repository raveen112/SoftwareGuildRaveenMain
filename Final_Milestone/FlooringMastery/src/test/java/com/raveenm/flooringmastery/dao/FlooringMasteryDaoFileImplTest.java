/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Order;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ravee
 */
public class FlooringMasteryDaoFileImplTest {

    FlooringMasteryDao testDao;

    public FlooringMasteryDaoFileImplTest() {
        String prefix = "Tests/FileOrderDao/Orders_";
        testDao = new FlooringMasteryDaoFileImpl(prefix);
    }

    @BeforeEach
    public void setUp() throws FileNotFoundException, IOException {
        String prefix = "Tests/FileOrderDao/Orders_";
        String testFile = "02282022.txt";
        PrintWriter out = new PrintWriter(new FileWriter(prefix + testFile));

        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total\n"
                + "1,Yahoo Answers,WA,9.25,Wood,3712.00,5.15,4.75,19116.80,17632.00,3399.26,40148.06");

        out.flush();
        out.close();

    }

    @Test
    public void testAddGetOrders() throws Exception {
        int orderNumber = 0;
        String customerName = "Justin Timberu";
        String stateAbbreviation = "GA";
        BigDecimal taxRate = new BigDecimal("6.00").setScale(2, RoundingMode.HALF_UP);
        String productName = "Carpet";
        BigDecimal area = new BigDecimal("2143.00").setScale(2, RoundingMode.HALF_UP);
        BigDecimal costPerSqFt = new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCostPerSqFt = new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP);
        BigDecimal materialCost = new BigDecimal("4821.75").setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCost = new BigDecimal("4500.30").setScale(2, RoundingMode.HALF_UP);
        BigDecimal tax = new BigDecimal("559.32").setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = new BigDecimal("9881.37").setScale(2, RoundingMode.HALF_UP);
        LocalDate date = LocalDate.parse("02282022", DateTimeFormatter.ofPattern("MMddyyyy"));

        Order order2 = new Order(orderNumber, customerName, stateAbbreviation, taxRate, productName, area, costPerSqFt, laborCostPerSqFt, materialCost, laborCost, tax, total, date);

        testDao.addOrder(order2);
        List<Order> allOrders = testDao.getAllOrders(date);

        assertEquals(allOrders.size(), 2, "There must be two Orders");
        assertTrue(allOrders.contains(order2), "Order2 must be in allOrders");

    }

    @Test
    public void testEditOrders() throws FlooringMasteryDaoException {
        int orderNumber = 1;
        String customerName = "Justin Timberu";
        String stateAbbreviation = "GA";
        BigDecimal taxRate = new BigDecimal("6.00").setScale(2, RoundingMode.HALF_UP);
        String productName = "Carpet";
        BigDecimal area = new BigDecimal("2143.00").setScale(2, RoundingMode.HALF_UP);
        BigDecimal costPerSqFt = new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCostPerSqFt = new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP);
        BigDecimal materialCost = new BigDecimal("4821.75").setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCost = new BigDecimal("4500.30").setScale(2, RoundingMode.HALF_UP);
        BigDecimal tax = new BigDecimal("559.32").setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = new BigDecimal("9881.37").setScale(2, RoundingMode.HALF_UP);
        LocalDate date = LocalDate.parse("02282022", DateTimeFormatter.ofPattern("MMddyyyy"));

        Order editedOrder = new Order(orderNumber, customerName, stateAbbreviation, taxRate, productName, area, costPerSqFt, laborCostPerSqFt, materialCost, laborCost, tax, total, date);

        editedOrder = testDao.editOrder(editedOrder);
        List<Order> allOrders = testDao.getAllOrders(date);

        assertTrue(allOrders.contains(editedOrder), "Original order must be replaced by edited order");
        assertEquals(allOrders.size(), 1, "allOrders must contain only 1 entry");
    }

    @Test
    public void testDeleteOrders() throws FlooringMasteryDaoException {
        int orderNumber = 0;
        String customerName = "Justin Timberu";
        String stateAbbreviation = "GA";
        BigDecimal taxRate = new BigDecimal("6.00").setScale(2, RoundingMode.HALF_UP);
        String productName = "Carpet";
        BigDecimal area = new BigDecimal("2143.00").setScale(2, RoundingMode.HALF_UP);
        BigDecimal costPerSqFt = new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCostPerSqFt = new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP);
        BigDecimal materialCost = new BigDecimal("4821.75").setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCost = new BigDecimal("4500.30").setScale(2, RoundingMode.HALF_UP);
        BigDecimal tax = new BigDecimal("559.32").setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = new BigDecimal("9881.37").setScale(2, RoundingMode.HALF_UP);
        LocalDate date = LocalDate.parse("02282022", DateTimeFormatter.ofPattern("MMddyyyy"));

        Order orderToDelete = new Order(orderNumber, customerName, stateAbbreviation, taxRate, productName, area, costPerSqFt, laborCostPerSqFt, materialCost, laborCost, tax, total, date);

        //testDao will modify the order number so it must be assigned.
        orderToDelete = testDao.addOrder(orderToDelete);
        testDao.removeOrder(orderToDelete);
        List<Order> allOrders = testDao.getAllOrders(date);

        assertEquals(allOrders.size(), 1, "allOrders must contain 1 order");
        assertFalse(allOrders.contains(orderToDelete), "allOrders must not contain orderToDelete");
    }
}
