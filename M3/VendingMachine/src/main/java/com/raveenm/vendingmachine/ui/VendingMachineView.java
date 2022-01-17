/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.ui;

import com.raveenm.vendingmachine.dao.VendingMachineDaoException;
import com.raveenm.vendingmachine.dto.Inventory;
import com.raveenm.vendingmachine.service.Funds;
import com.raveenm.vendingmachine.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ravee
 */
public class VendingMachineView {

    private UserIO io = new UserIOConsoleImpl();
    VendingMachineServiceLayer service;

    public int enterExit() {
        io.print("Select a menu option: ");
        io.print("1. Deposit Money");
        io.print("2. Exit");

        return io.readInt("Enter your choice: ", 1, 2);
    }

    public String getItemSelection() throws VendingMachineDaoException {
        String itemChoice = io.readString("Enter your Item Choice:");
        return itemChoice;
    }

    public void printMenu(List<Inventory> inventoryList) {
        io.print("Vending Machine: ");

        for (Inventory item : inventoryList) {

            if (item.getItemCount() > 0) {
                io.print(item.getId() + " " + "Item: " + item.getItemName() + " " + "Cost: " + item.getItemCost() + " " + "Stock: " + item.getItemCount());
            }

        }

    }

    public void printErrorMessage(Exception e) {
        io.print(e.getMessage());
    }

    // Change calculation logic
    // Call the enum from the dto  
    public void returnBalance(BigDecimal returnAmount) {
        io.print("=== Change ===" + "\n");
        BigDecimal[] changeRemainder = returnAmount.divideAndRemainder(Funds.ONE_DOLLAR.getVALUE());
        returnAmount = changeRemainder[1];
        io.print(Funds.ONE_DOLLAR.getNAME() + " : " + changeRemainder[0]);
        changeRemainder = returnAmount.divideAndRemainder(Funds.TWENTY_FIVE_CENTS.getVALUE());
        returnAmount = changeRemainder[1];
        io.print(Funds.TWENTY_FIVE_CENTS.getNAME() + " : " + changeRemainder[0]);
        changeRemainder = returnAmount.divideAndRemainder(Funds.TEN_CENTS.getVALUE());
        returnAmount = changeRemainder[1];
        io.print(Funds.TEN_CENTS.getNAME() + " : " + changeRemainder[0]);
        changeRemainder = returnAmount.divideAndRemainder(Funds.FIVE_CENTS.getVALUE());
        returnAmount = changeRemainder[1];
        io.print(Funds.FIVE_CENTS.getNAME() + " : " + changeRemainder[0]);
        changeRemainder = returnAmount.divideAndRemainder(Funds.ONE_CENT.getVALUE());
        returnAmount = changeRemainder[1];
        io.print(Funds.ONE_CENT.getNAME() + " : " + changeRemainder[0]);

        io.print("Change due: " + returnAmount + "\n");
    }

    public BigDecimal depositMoney() throws NumberFormatException {
        String funds;
        BigDecimal inputedCash = new BigDecimal("0.00");
        Boolean errorInput = true;
        while (errorInput) {
            try {
                funds = io.readString("Enter your money here: ");
                inputedCash = new BigDecimal(funds);
                //changing the value of inputed cash
                if (inputedCash.compareTo(new BigDecimal("0")) < 0) {
                    io.print("Error: Please enter a +ve numeric amount.");
                } else {

                    errorInput = false;
                }
            } catch (NumberFormatException e) {

                io.print("Error: Please enter +ve numeric amount.");
            }

        }
        return inputedCash;
    }

    public void succesfullyDispensedBanner(Inventory itemDispensed, BigDecimal returnBalance) {
        io.print("== Succesfully dispensed " + itemDispensed.getItemName() + "=== " + "\n");
        io.print(("=== Balance: " + returnBalance + " ====" + "\n"));
    }
}
