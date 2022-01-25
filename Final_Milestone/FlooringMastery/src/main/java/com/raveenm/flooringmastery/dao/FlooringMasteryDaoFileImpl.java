/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author ravee
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    List<Order> allOrders = new ArrayList<>();
    String DELIMITER = ",";
    // 5. marshall
    // 2. unmarshall
    // 3. getAllOrders
    // 8. removeOrder
    // 4. writeOrder
    // 6. addOrder
    // 7. editOrder
    // 1. loadOrders

    // Unmarshall from txt file 
    private Order unmarshallOrder(String orderString, LocalDate dateStamp) {
        // change DTO to create an order split
        String[] fieldArray = orderString.split(DELIMITER);
        int orderNumber = Integer.parseInt(fieldArray[0]);
        String customerName = fieldArray[1];
        String state = fieldArray[2];
        BigDecimal taxRate = new BigDecimal(fieldArray[3]).setScale(2, RoundingMode.HALF_UP);
        String productType = fieldArray[4];
        BigDecimal area = new BigDecimal(fieldArray[5]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal costPerSquareFoot = new BigDecimal(fieldArray[6]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(fieldArray[7]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal materialCost = new BigDecimal(fieldArray[8]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCost = new BigDecimal(fieldArray[9]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxFinal = new BigDecimal(fieldArray[10]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalCost = new BigDecimal(fieldArray[11]).setScale(2, RoundingMode.HALF_UP);

        return new Order(orderNumber,
                customerName,
                state,
                taxRate,
                productType,
                area,
                costPerSquareFoot,
                laborCostPerSquareFoot,
                materialCost,
                laborCost,
                taxFinal,
                totalCost,
                dateStamp);
    }

    //load orders (READ)
    private void loadOrders(LocalDate dateStamp) throws FlooringMasteryDaoException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = dateStamp.format(formatter);
        Scanner scanner;
        // to go through the file directory
        String fileName = "Orders/Orders_" + dateString + ".txt";

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));

        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDaoException("Orders not found for this date");
        }
        String currentLine;
        Order currentOrder;
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentOrder = unmarshallOrder(currentLine, dateStamp);
            allOrders.add(currentOrder);
        }
        scanner.close();
    }

    // 3. list all items
    public List<Order> getAllOrders(LocalDate queryDate) throws FlooringMasteryDaoException {

        allOrders.clear();
        loadOrders(queryDate);
        return this.allOrders;

    }

    private String marshallOrder(Order order) {
        String lineEntry = "";

        lineEntry += order.getOrderNumber() + DELIMITER;
        lineEntry += order.getCustomerName() + DELIMITER;
        lineEntry += order.getState() + DELIMITER;
        lineEntry += order.getTaxRate() + DELIMITER;
        lineEntry += order.getProductType() + DELIMITER;
        lineEntry += order.getArea() + DELIMITER;
        lineEntry += order.getCostPerSquareFoot() + DELIMITER;
        lineEntry += order.getCostPerSquareFoot() + DELIMITER;
        lineEntry += order.getMaterialCost() + DELIMITER;
        lineEntry += order.getLaborCost() + DELIMITER;
        lineEntry += order.getTaxFinal() + DELIMITER;
        lineEntry += order.getTotalCost() + DELIMITER;

        return lineEntry;

    }

    //writeOrder() need to check if file exists
    private void writeOrders(LocalDate queryDate) throws FlooringMasteryDaoException {
        PrintWriter out;
        String fileName;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = queryDate.format(formatter);
        fileName = "Orders/Orders_" + dateString + ".txt";
        try {
            out = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException("Could not save inventory data.", e);
        }

        String itemList;

        for (Order currentOrder : allOrders) {
            // turn a inventory into a String
            itemList = marshallOrder(currentOrder);
            // write the inventory  object to the file
            out.println(itemList);
            // force PrintWriter to write line to the file
            out.flush();
        }

        out.close();

    }

    public Order addOrder(Order placeOrder) {
        int orderNumber = 1;
        int maxOrderNumber = 0;
        try {
            List<Order> orders = getAllOrders(placeOrder.getOrderDate());
            for (Order order : orders) {
                int orderNum = order.getOrderNumber();
                if (orderNum > maxOrderNumber) {
                    maxOrderNumber = orderNum;
                }
            }
            orderNumber = maxOrderNumber + 1;
            placeOrder.setOrderNumber(orderNumber);

        } catch (FlooringMasteryDaoException e) {
            placeOrder.setOrderNumber(orderNumber);
        }

        allOrders.add(placeOrder);

        return placeOrder;

    }

    // writeOrder() need to check if file exists
    // validate in service layer and view
}
