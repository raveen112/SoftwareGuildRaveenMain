/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.service;

import com.raveenm.vendingmachine.dao.VendingMachineAuditDao;
import com.raveenm.vendingmachine.dao.VendingMachineDao;
import com.raveenm.vendingmachine.dao.VendingMachineDaoException;
import com.raveenm.vendingmachine.dto.Inventory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author ravee
 */
public class VendingMachineServiceLayerFileImpl implements VendingMachineServiceLayer {

    BigDecimal fundsDeposited = new BigDecimal("0").setScale(2, RoundingMode.UP);
    VendingMachineDao dao;

    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerFileImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {              // 1. constructor for dao
        this.dao = dao;
        this.auditDao = auditDao;
    }

    // add test: all items must be marshalled 
    public List<Inventory> getAllItems() throws VendingMachineDaoException {  // 2. Dao to get all available items, remove items 
        List<Inventory> availableItems = dao.getAllItems();                         // sort through availableItems array and remove when -- 0                   

        return availableItems;

    }

    @Override
    public void depositFunds(BigDecimal funds) {

        this.fundsDeposited = this.fundsDeposited.add(funds);
        // funds held in the funds

    }

    // add test 
    public BigDecimal getBalance() {
        return this.fundsDeposited;
    }

    // add test: check balance calculations
    //deducting the funds inputted by the cost; balance is saved
    public void deductFunds(Inventory item) {
        BigDecimal deduction = item.getItemCost();
        this.fundsDeposited = this.fundsDeposited.subtract(deduction);

    }

    // add test 
    //return customers change
    public BigDecimal returnAmount() throws VendingMachineDaoException {
        BigDecimal amountReturned = this.fundsDeposited;
        fundsDeposited = new BigDecimal("0").setScale(2, RoundingMode.UP);
        auditDao.writeAuditEntry("Change " + amountReturned + "Returned.");
        return amountReturned;

    }

    // add test 
    // calls the dao methods, deduct and dispensed item with the -1 logic 
    @Override
    public Inventory dispenseItem(String id) throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException {
        Inventory itemToPurchase = dao.getSingleItem(id);
        //order of exceptions changes the service layer test exception 
        checkInventory(itemToPurchase);
        checkFundAmount(itemToPurchase);
        Inventory dispensedItem = dao.dispenseItem(id);

        auditDao.writeAuditEntry(("Item: " + dispensedItem.getItemName() + " from " + dispensedItem.getId() + " DISPENSED"));
        this.deductFunds(dispensedItem);
        return dispensedItem;

    }

    //inventory verification
    public void checkInventory(Inventory item) throws NoItemInventoryException {
        if (item.getItemCount() == 0) {
            throw new NoItemInventoryException("We ran out!");

        }
    }

    //check the validity of the funds (amount entered vs. cost of item)
    public void checkFundAmount(Inventory item) throws InsufficientFundsException {
        if (item.getItemCost().compareTo(this.fundsDeposited) == 1) {
            throw new InsufficientFundsException("Insufficient funds for this purchase");
        }

    }

}
