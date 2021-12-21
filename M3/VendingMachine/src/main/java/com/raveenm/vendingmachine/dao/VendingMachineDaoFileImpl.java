/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.dao;

import com.raveenm.vendingmachine.dto.Inventory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Inventory> inventory = new HashMap<>();
    public static final String DELIMITER = "::";

    private void readInventory() throws VendingMachineDaoException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("items.txt")));

        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException("Could not load data!", e);
        }
        String currentLine;
        Inventory currentInventory;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentInventory = unmarshallInventory(currentLine);
            inventory.put(currentInventory.getId(), currentInventory);
        }
        scanner.close();
    }

    private Inventory unmarshallInventory(String item) {
        String[] itemArray = item.split(DELIMITER);

        String id = itemArray[0];
        String itemName = itemArray[1];
        BigDecimal itemCost = new BigDecimal(itemArray[2]).setScale(2, RoundingMode.UP);
        int itemCount = Integer.parseInt(itemArray[3]);

        Inventory currentInventory = new Inventory(id, itemName, itemCost, itemCount);
        return currentInventory;

    }
    @Override
    public Inventory getSingleItem(String id) throws VendingMachineDaoException{
        readInventory();
        Inventory singleItem = this.inventory.values().stream().filter(p -> p.getId().equals(id)).findAny().get();
        return singleItem;
    }
    
    @Override
    public List<Inventory> getAllItems() throws VendingMachineDaoException {
        readInventory();
        List<Inventory> inventoryList = new ArrayList<>(inventory.values());
        return inventoryList;

    }

    private String marshallInventory(Inventory item) {
        String mainEntry = "";
        mainEntry += item.getId() + DELIMITER;
        mainEntry += item.getItemName() + DELIMITER;
        mainEntry += item.getItemCost() + DELIMITER;
        mainEntry += item.getItemCount();

        return mainEntry;

    }

    private void writeInventory() throws VendingMachineDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("items.txt"));
        } catch (IOException e) {
            throw new VendingMachineDaoException("Could not save inventory data.", e);
        }

        String itemList;
        List<Inventory> inventoryList = this.getAllItems();
        for (Inventory currentInventory : inventoryList) {
            // turn a inventory into a String
            itemList = marshallInventory(currentInventory);
            // write the inventory  object to the file
            out.println(itemList);
            // force PrintWriter to write line to the file
            out.flush();
        }

        out.close();

    }

    @Override
    public Inventory dispenseItem(String id) throws VendingMachineDaoException {
        Inventory item = this.inventory.get(id);
        item.setItemCount(this.inventory.get(id).getItemCount() - 1);             //minus inventory by 1 for every call
        writeInventory();
        return item;
    }
    
    @Override
    public int getStock(Inventory item){
        return item.getItemCount();
    }
    
    
}
