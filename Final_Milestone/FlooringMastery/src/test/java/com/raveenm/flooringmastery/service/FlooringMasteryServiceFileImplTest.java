/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.service;

import com.raveenm.flooringmastery.dao.FlooringMasteryDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoException;
import com.raveenm.flooringmastery.dao.FlooringMasteryOrderDaoStubImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDaoStubImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDaoStubImpl;
import com.raveenm.flooringmastery.dao.OrderPersistenceException;
import com.raveenm.flooringmastery.dto.Order;
import com.raveenm.flooringmastery.dto.Product;
import com.raveenm.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ravee
 */
public class FlooringMasteryServiceFileImplTest {

    FlooringMasteryService service;

    public FlooringMasteryServiceFileImplTest() {
        FlooringMasteryDao orderDao = new FlooringMasteryOrderDaoStubImpl();
        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoStubImpl();
        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoStubImpl();
        service = new FlooringMasteryServiceFileImpl(orderDao, taxDao, productDao);
    }

    @Test
    public void testGetAllOrders() throws OrderPersistenceException, FlooringMasteryDaoException, OrdersNotFoundException {

        //Arrange
        Order order1 = new Order(1, "Yahoo Answers", "WA", new BigDecimal("9.25"), "Wood", new BigDecimal("3712.00"), new BigDecimal("5.15"), new BigDecimal("4.75"),
                new BigDecimal("19116.80"), new BigDecimal("17632.00"), new BigDecimal("3399.26"), new BigDecimal("40148.06"), LocalDate.now().plusDays(1));
        Order order2 = new Order(2, "Justin Timberu", "GA", new BigDecimal("6.00"), "Carpet", new BigDecimal("2143.00"), new BigDecimal("2.25"), new BigDecimal("2.10"),
                new BigDecimal("4821.75"), new BigDecimal("4500.00"), new BigDecimal("559.32"), new BigDecimal("9881.37"), LocalDate.now().plusDays(1));

        //Assert
        assertEquals(2, service.getAllOrders(LocalDate.now().plusDays(1)).size(), "Should have 2 orders");
        assertTrue(service.getAllOrders(LocalDate.now().plusDays(1)).contains(order1), "Order should contain order 1");
        assertTrue(service.getAllOrders(LocalDate.now().plusDays(1)).contains(order2), "Order should contain order 2");

    }

    @Test
    public void testGetProduct() throws OrderPersistenceException {
        //Arrange
        Product productCarpet = new Product("Carpet", new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP), new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));

        //Act
        Product shouldBeCarpet = service.getProductType(productCarpet.getProductType());

        //Assert
        assertNotNull(shouldBeCarpet, "Getting Carpet should not be null.");
        assertEquals(productCarpet, shouldBeCarpet, "Carpet should be stored under carpet.");

        Product shouldBeNull = service.getProductType("Gold");
        assertNull(shouldBeNull, "Getting Gold should be null");

    }

    @Test
    public void testGetAllProducts() throws OrderPersistenceException {
        //Arrange
        Product productCarpet = new Product("Carpet", new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP), new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        Product productLaminate = new Product("Laminate", new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP), new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        Product productTile = new Product("Tile", new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP), new BigDecimal("4.15").setScale(2, RoundingMode.HALF_UP));
        Product productWood = new Product("Wood", new BigDecimal("5.15").setScale(2, RoundingMode.HALF_UP), new BigDecimal("4.75").setScale(2, RoundingMode.HALF_UP));

        //Act
        assertEquals(4, service.getallProductTypes().size(), "Should only have 4 products");
        assertTrue(service.getallProductTypes().contains(productCarpet), "This should contain Carpet");
        assertTrue(service.getallProductTypes().contains(productLaminate), "This should contain Laminate");
        assertTrue(service.getallProductTypes().contains(productTile), "This should contain Tile");
        assertTrue(service.getallProductTypes().contains(productWood), "This should contain Wood");
    }

    @Test
    public void testGetTax() throws OrderPersistenceException {
        //Arrange
        Tax taxCali = new Tax("CA", "California", new BigDecimal("25.00").setScale(2, RoundingMode.HALF_UP));

        //Act
        Tax shouldBeCali = service.getStateTax("CA");

        //Assert
        assertNotNull(shouldBeCali, "Getting CA should not be null");
        assertEquals(taxCali, shouldBeCali, "Tax stored under CA should be California");

        Tax shouldBeNull = service.getStateTax("KI");
        assertNull(shouldBeNull, "Getting KI should be null.");
    }

    @Test
    public void testGetAllTaxes() throws OrderPersistenceException {
        //Arrange
        Tax taxTexas = new Tax("TX", "Texas", new BigDecimal("4.45").setScale(2, RoundingMode.HALF_UP));
        Tax taxWashington = new Tax("WA", "Washington", new BigDecimal("9.25").setScale(2, RoundingMode.HALF_UP));
        Tax taxKentucky = new Tax("KY", "Kentucky", new BigDecimal("6.00").setScale(2, RoundingMode.HALF_UP));
        Tax taxCalifornia = new Tax("CA", "California", new BigDecimal("25.00").setScale(2, RoundingMode.HALF_UP));

        //Act
        assertEquals(4, service.getAllStateTaxes().size(), "Should only have 4 states");
        assertTrue(service.getAllStateTaxes().contains(taxTexas), "This should contain TX");
        assertTrue(service.getAllStateTaxes().contains(taxWashington), "This should contain WA");
        assertTrue(service.getAllStateTaxes().contains(taxKentucky), "This should contain KY");
        assertTrue(service.getAllStateTaxes().contains(taxCalifornia), "This should contain CA");
    }

    @Test
    public void testGetOrderSummary() throws OrderPersistenceException {
        //Arrange
        Order order1 = new Order("Yahoo Answers", "WA", "Wood", new BigDecimal("3712.00"), LocalDate.now().plusDays(1));

        try {
            Order processedOrder = service.getOrderSummary(order1);
            Product orderProduct = service.getProductType(order1.getProductType());
            Tax orderTax = service.getStateTax(order1.getState());

            //Assert
            assertTrue(processedOrder.getLaborCost().equals(orderProduct.getLaborCostPerSquareFoot().multiply(order1.getArea()).setScale(2, RoundingMode.HALF_UP)), "Processed order labor cost must equal the ordered product labor cost per sq ft times the area");
            assertTrue(processedOrder.getMaterialCost().equals(orderProduct.getCostPerSquareFoot().multiply(order1.getArea()).setScale(2, RoundingMode.HALF_UP)), "Processed order material cost must equal the ordered product materiak cost per sq ft times the area");
            assertTrue(processedOrder.getTaxRate().equals(orderTax.getRawTax()));

            BigDecimal taxRate = orderTax.getRawTax().divide(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
            BigDecimal laborTax = taxRate.multiply(processedOrder.getLaborCost().setScale(2, RoundingMode.HALF_UP));
            BigDecimal materialTax = taxRate.multiply(processedOrder.getMaterialCost().setScale(2, RoundingMode.HALF_UP));

            assertTrue(processedOrder.getTaxFinal().equals(laborTax.add(materialTax).setScale(2, RoundingMode.HALF_UP)), "Processed order tax final must equal ordered product tax rate times material cost plus labor cost");

            BigDecimal totalTax = laborTax.add(materialTax.setScale(2, RoundingMode.HALF_UP));
            BigDecimal totalPreTaxCost = processedOrder.getMaterialCost().add(processedOrder.getLaborCost().setScale(2, RoundingMode.HALF_UP));
            BigDecimal totalCost = totalTax.add(totalPreTaxCost.setScale(2, RoundingMode.HALF_UP));

            assertTrue(processedOrder.getTotalCost().equals(totalCost.setScale(2, RoundingMode.HALF_UP)));

        } catch (InvalidDateException | StateNotFoundException | ProductNotFoundException | InvalidCustomerNameException | OrderPersistenceException | InsufficientSquareFootageException e) {
            fail("Order was valid," + e.getClass() + " should not have been thrown!");
        }

    }

    @Test
    public void testInvalidDateGetSummary() {
        Order order1 = new Order("Yahoo Answers", "WA", "Wood", new BigDecimal("3712.00"), LocalDate.now());

        try {
            service.getOrderSummary(order1);
            fail("InvalidDateException was not thrown");
        } catch (StateNotFoundException | ProductNotFoundException | InvalidCustomerNameException | OrderPersistenceException | InsufficientSquareFootageException e) {
            fail("Incorrect exception was thrown.");

        } catch (InvalidDateException e) {
            return;
        }

    }

    @Test
    public void testInvalidStateGetSummary() {
        Order order1 = new Order("Yahoo Answers", "SW", "Wood", new BigDecimal("3712.00"), LocalDate.now().plusDays(1));

        try {
            service.getOrderSummary(order1);
            fail("StateNotFoundException was not thrown");
        } catch (InvalidDateException | ProductNotFoundException | InvalidCustomerNameException | OrderPersistenceException | InsufficientSquareFootageException e) {
            fail("Incorrect exception was thrown.");

        } catch (StateNotFoundException e) {
            return;
        }

    }

    @Test
    public void testInvalidProductGetSummary() {
        Order order1 = new Order("Yahoo Answers", "CA", "Wooded", new BigDecimal("3712.00"), LocalDate.now().plusDays(1));

        try {
            service.getOrderSummary(order1);
            fail("ProductNotFoundException was not thrown");
        } catch (InvalidDateException | StateNotFoundException | InvalidCustomerNameException | OrderPersistenceException | InsufficientSquareFootageException e) {
            fail("Incorrect exception was thrown.");

        } catch (ProductNotFoundException e) {
            return;
        }

    }

    @Test
    public void testInvalidSqFootagetGetSummary() {
        Order order1 = new Order("Yahoo Answers", "CA", "Wood", new BigDecimal("99.99"), LocalDate.now().plusDays(1));

        try {
            service.getOrderSummary(order1);
            fail("InsufficientSquareFootageException was not thrown");
        } catch (InvalidDateException | StateNotFoundException | InvalidCustomerNameException | OrderPersistenceException | ProductNotFoundException e) {
            fail("Incorrect exception was thrown.");

        } catch (InsufficientSquareFootageException e) {
            return;
        }
    }

    @Test
    public void testInvalidCustomerNameGetSummary() {
        Order order1 = new Order("{|}w", "CA", "Wood", new BigDecimal("2141"), LocalDate.now().plusDays(1));

        try {
            service.getOrderSummary(order1);
            fail("InvalidCustomerNameException was not thrown");
        } catch (InvalidDateException | StateNotFoundException | InsufficientSquareFootageException | OrderPersistenceException | ProductNotFoundException e) {
            fail("Incorrect exception was thrown.");

        } catch (InvalidCustomerNameException e) {
            return;
        }
    }

    @Test
    public void testAddOrder() {
        Order order1 = new Order("Yahoo Answers", "WA", "Wood", new BigDecimal("3712.00"), LocalDate.now().plusDays(1));

        try {
            Order summaryOrder = service.getOrderSummary(order1);
            Order addedOrder = service.addOrder(summaryOrder);

            //Assert
            assertNotNull(addedOrder, "Added order should not equal null");

        } catch (FlooringMasteryDaoException | InvalidDateException | StateNotFoundException | InsufficientSquareFootageException | OrderPersistenceException | ProductNotFoundException | InvalidCustomerNameException e) {
            fail("Invalid exception " + e.getClass() + " was thrown");
        }

    }

    @Test
    public void testGetEditOrder() {

        try {
            List<Order> orders = service.getAllOrders(LocalDate.now().plusDays(1));

            Order orderToEdit = orders.get(0);
            orderToEdit.setCustomerName("Joseph");
            orderToEdit.setState("KY");
            orderToEdit.setProductType("Laminate");
            orderToEdit.setArea(new BigDecimal("8534").setScale(2, RoundingMode.HALF_UP));

            Order editedOrder = service.getOrderSummary(orderToEdit);
            editedOrder = service.editOrder(editedOrder);

            //Assert
            assertNotNull(editedOrder, "Edited order should not be null");
            assertEquals(orderToEdit, editedOrder, "orderToEdit and editedOrder must be equal");
        } catch (OrdersNotFoundException | FlooringMasteryDaoException | InvalidDateException | StateNotFoundException | InsufficientSquareFootageException | OrderPersistenceException | ProductNotFoundException | InvalidCustomerNameException e) {
            fail("Invalid exception " + e.getClass() + " was thrown");
        }
    }

    @Test
    public void testGetRemoveOrder() {

        try {
            List<Order> orders = service.getAllOrders(LocalDate.now().plusDays(1));
            Order orderToRemove = orders.get(0);
            Order removedOrder = service.removeOrder(orderToRemove);

            assertNotNull(removedOrder, "Removed order should not be null");
            assertEquals(orderToRemove, removedOrder, "orderToRemove should be equal to removedOrder.");

        } catch (FlooringMasteryDaoException | OrdersNotFoundException e) {
            fail("Invalid exception " + e.getClass() + " was thrown");
        }
    }

}
