/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.dao;

import com.raveenm.vendingmachine.dto.Inventory;
import com.raveenm.vendingmachine.service.NoItemInventoryException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ravee
 */
// add exceptions!!!
public interface VendingMachineDao {

    List<Inventory> getAllItems() throws VendingMachineDaoException;  // read 

    public Inventory dispenseItem(String id) throws VendingMachineDaoException;  //update method 

    public Inventory getSingleItem(String id) throws VendingMachineDaoException;

    public int getStock(Inventory item);
}
