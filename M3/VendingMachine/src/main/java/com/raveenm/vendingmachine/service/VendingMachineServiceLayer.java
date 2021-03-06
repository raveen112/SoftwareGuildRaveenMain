/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.service;

import com.raveenm.vendingmachine.dao.VendingMachineDaoException;
import com.raveenm.vendingmachine.dto.Inventory;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface VendingMachineServiceLayer {
    
    public List<Inventory> getAllItems() throws VendingMachineDaoException;
    public Inventory dispenseItem(String id) throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException;
    public void depositFunds(BigDecimal funds);
    public BigDecimal getBalance() throws VendingMachineDaoException;
    public BigDecimal returnAmount() throws VendingMachineDaoException;
}