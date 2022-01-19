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
import com.raveenm.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;

/**
 *
 * @author ravee
 */
public class VendingMachineController {

    VendingMachineServiceLayer service;
    VendingMachineView view;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;

    }

    public void run() throws VendingMachineDaoException, InsufficientFundsException, NoItemInventoryException {
        boolean keepGoing = true;
        boolean outOfStock;
        view.printMenu(service.getAllItems());
        while (keepGoing) {

            try {
                //add funds to the machine
                int menuSelection = view.enterExit();

                switch (menuSelection) {
                    case 1:
                        depositFunds();
                        itemDispensed();
                        break;

                    // add change post purchase as the exit message
                    case 2:
                        keepGoing = false;
                        returnBalance();
                        break;

                }
            } catch (NoItemInventoryException | InsufficientFundsException e) {

                view.printErrorMessage(e);

            }

        }

    }

    public String getItemSelection() throws VendingMachineDaoException {
        BigDecimal balance = service.getBalance();
        view.displayBalance(balance);
        return view.getItemSelection();

    }

    public void depositFunds() {
        BigDecimal funds = view.depositMoney();
        service.depositFunds(funds);
    }

    public void returnBalance() throws VendingMachineDaoException {
        BigDecimal balance = service.getBalance();
        view.returnBalance(balance);
    }

    public void itemDispensed() throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException {
        Inventory itemDispensed = service.dispenseItem(getItemSelection());
        BigDecimal returnBalance = service.getBalance();
        view.succesfullyDispensedBanner(itemDispensed, returnBalance);

    }

}
