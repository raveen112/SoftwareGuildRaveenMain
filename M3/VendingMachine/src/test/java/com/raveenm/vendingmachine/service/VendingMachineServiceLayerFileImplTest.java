/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.service;

import com.raveenm.vendingmachine.dao.VendingMachineAuditDao;
import com.raveenm.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import com.raveenm.vendingmachine.dao.VendingMachineDao;
import com.raveenm.vendingmachine.dao.VendingMachineDaoException;
import com.raveenm.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.raveenm.vendingmachine.dto.Inventory;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ravee
 */
public class VendingMachineServiceLayerFileImplTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerFileImplTest() {
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//
//        service = new VendingMachineServiceLayerFileImpl(dao, auditDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("vendingMachineService", VendingMachineServiceLayer.class);
    }

    //
    @Test
    public void testGetAllItems() throws VendingMachineDaoException {
        Inventory item1 = new Inventory("1001", "Dr.Pepper", new BigDecimal("1.25"), 3);
        Inventory item2 = new Inventory("1002", "Fries", new BigDecimal("1.00"), 0);

        assertEquals(2, service.getAllItems().size(), "Should have only 2 items.");
        assertTrue(service.getAllItems().contains(item1));
        assertTrue(service.getAllItems().contains(item2));

    }

    @Test
    public void testDispenseItem() throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException {
        Inventory item1 = new Inventory("1001", "Dr.Pepper", new BigDecimal("1.25"), 3);

        service.depositFunds(item1.getItemCost());
        Inventory dispensedItem = service.dispenseItem("1001");
        assertEquals(dispensedItem.getItemCount(), item1.getItemCount() - 1);
        service.returnAmount();

    }

    @Test
    public void testNoItemInventoryException() throws VendingMachineDaoException, InsufficientFundsException, NoItemInventoryException {
        Inventory item1 = new Inventory("1001", "Dr.Pepper", new BigDecimal("1.25"), 3);
        Inventory item2 = new Inventory("1002", "Fries", new BigDecimal("1.00"), 0);  // to test the no inventory exception

        //ACT and ASSERT
        try {
            Inventory shouldThrowError = service.dispenseItem("1002");
            fail("Dispensing zero inventory items should throw an error");

        } catch (InsufficientFundsException e) {
            fail("Incorrect execption was thrown.");
        } catch (NoItemInventoryException e) {
            return;
        }

    }

    @Test
    public void testInsufficientFundsException() throws VendingMachineDaoException {
        Inventory item1 = new Inventory("1001", "Dr.Pepper", new BigDecimal("1.25"), 3);
        Inventory item2 = new Inventory("1002", "Fries", new BigDecimal("1.00"), 0);  // to test the no inventory exception

        // funds deposited less than cost of the item (item 2 is cheaper than item 1)
        service.depositFunds(item2.getItemCost());
        //ACT and ASSERT
        try {
            Inventory shouldThrowError = service.dispenseItem(item1.getId());
            fail("Should fail as there are insufficient funds.");

        } catch (NoItemInventoryException e) {
            fail("Incorrect execption was thrown.");
        } catch (InsufficientFundsException e) {
            return;
        }
    }

    //Verify change returned, and check for 0.00 balance at the end.
    @Test
    public void testChangeReturn() throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException {
        Inventory item1 = new Inventory("1001", "Dr.Pepper", new BigDecimal("1.25"), 3);

        service.depositFunds(new BigDecimal("10.00"));
        service.dispenseItem("1001");
        assertTrue(service.getBalance().equals(new BigDecimal("8.75")));

        assertTrue(service.returnAmount().equals(new BigDecimal("8.75")));
        assertTrue(service.getBalance().equals(new BigDecimal("0.00")));
    }

}
