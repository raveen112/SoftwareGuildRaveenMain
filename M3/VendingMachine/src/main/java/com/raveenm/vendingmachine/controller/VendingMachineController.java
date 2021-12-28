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
import com.raveenm.vendingmachine.ui.InputErrorException;
import com.raveenm.vendingmachine.ui.UserIO;
import com.raveenm.vendingmachine.ui.UserIOConsoleImpl;
import com.raveenm.vendingmachine.ui.VendingMachineView;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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

    public void run() throws IOException, VendingMachineDaoException, InsufficientFundsException, NoItemInventoryException, InputErrorException {
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
        return view.getItemSelection();

    }

    public void depositFunds() throws InputErrorException {
        BigDecimal funds = view.depositMoney();
        service.depositFunds(funds);
    }

    public void returnBalance() throws VendingMachineDaoException, IOException {
        BigDecimal balance = service.getBalance();
        view.returnBalance(balance);
    }

    public void itemDispensed() throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException, IOException {
        Inventory itemDispensed = service.dispenseItem(getItemSelection());
        BigDecimal returnBalance = service.getBalance();
        view.succesfullyDispensedBanner(itemDispensed, returnBalance);

    }

}
