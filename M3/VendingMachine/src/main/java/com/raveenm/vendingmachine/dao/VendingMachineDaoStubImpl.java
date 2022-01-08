/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.dao;

import com.raveenm.vendingmachine.dto.Inventory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ravee
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    //class used to set the state of the file for the service layer test
    public Inventory firstInventoryItem = new Inventory("1001", "Dr.Pepper", new BigDecimal("1.25"), 3);
    public Inventory secondInventoryItem = new Inventory("1002", "Fries", new BigDecimal("1.00"), 0);

    @Override
    public List<Inventory> getAllItems() throws VendingMachineDaoException {
        List<Inventory> allItems = new ArrayList<>();
        allItems.add(firstInventoryItem);
        allItems.add(secondInventoryItem);
        return allItems;
    }

    @Override
    public Inventory dispenseItem(String id) throws VendingMachineDaoException {

        if (id == "1001") {
            return new Inventory(firstInventoryItem.getId(), firstInventoryItem.getItemName(), firstInventoryItem.getItemCost(), firstInventoryItem.getItemCount() - 1);

        }
        return null;
    }

    @Override
    public Inventory getSingleItem(String id) throws VendingMachineDaoException {
        if (id == "1001") {
            return firstInventoryItem;
        } else if (id == "1002") {
            return secondInventoryItem;
        }
        return null;
    }

    @Override
    public int getStock(Inventory item) {
        if (item.getId() == "1001") {
            return firstInventoryItem.getItemCount();
        } else if (item.getId() == "1002") {
            return secondInventoryItem.getItemCount();
        }
        return 0;

    }
}
