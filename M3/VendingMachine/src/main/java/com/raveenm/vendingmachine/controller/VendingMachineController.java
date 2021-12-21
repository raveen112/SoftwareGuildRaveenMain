/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.controller;

import com.raveenm.vendingmachine.dao.VendingMachineDaoException;
import com.raveenm.vendingmachine.dto.Inventory;
import com.raveenm.vendingmachine.service.InsufficientFundsException;
import com.raveenm.vendingmachine.service.NoItemInventoryException;
import com.raveenm.vendingmachine.service.VendingMachineServiceLayer;
import com.raveenm.vendingmachine.service.VendingMachineServiceLayerFileImpl;
import com.raveenm.vendingmachine.ui.UserIO;
import com.raveenm.vendingmachine.ui.UserIOConsoleImpl;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ravee
 */
public class VendingMachineController {
    
    private UserIO io = new UserIOConsoleImpl();
    VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
        
    }
    
    public void run() throws VendingMachineDaoException, InsufficientFundsException, NoItemInventoryException {
        boolean keepGoing = true;
        printMenu();
        while (keepGoing) {
            
           
            try {
                //add funds to the machine
                io.print("Select a menu option: ");
                io.print("1. Deposit Money");
                io.print("2. Exit");
                
                int menuSelection = io.readInt("Enter your choice: ", 1, 2);
                
                switch (menuSelection) {
                    case 1:
                        depositFunds();
                        Inventory itemDispensed = service.dispenseItem(getItemSelection());
                        io.print("Succesfully dispensed "+ itemDispensed.getItemName());
                        io.print("Balance: "+ service.getBalance());
                        
                        break;

                    // add change post purchase as the exit message
                    case 2:
                        keepGoing = false;
                        io.print("Returning balance: " + service.returnAmount());
                        break;
                    
                }
            } catch (InsufficientFundsException | NoItemInventoryException e) {
                io.print(e.getMessage());
                printMenu();
                io.print("Balance: "+ service.getBalance());
            }
            
        }
        
    }

    // add to view 
    public void printMenu() throws VendingMachineDaoException {
        io.print("Vending Machine: ");
        
        List<Inventory> inventoryList = service.getAllItems();
        
        for (Inventory item : inventoryList) {
            
            if (item.getItemCount() > 0) {
                io.print(item.getId() + " " + "Item: " + item.getItemName() + " " + "Cost: " + item.getItemCost() + " " + "Stock: " + item.getItemCount());
            }
            
        }
        
    }
    
    public String getItemSelection() throws VendingMachineDaoException {
        String itemChoice = io.readString("Enter your Item Choice:");
        return itemChoice;
    }
    
    public void depositFunds() {
        BigDecimal funds = new BigDecimal(io.readString("Deposit Money:"));
        service.depositFunds(funds);
    }
    
}
