/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
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
        //clear orders so it doesnt reprint in the text file
        allOrders.clear();
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

    @Override
    public Order getSingleOrder(int orderID) throws FlooringMasteryDaoException {
        return allOrders.get(orderID);
    }

    // getSingleOrder method
    // 3. list all items
    public List<Order> getAllOrders(LocalDate queryDate) throws FlooringMasteryDaoException {

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
        lineEntry += order.getLaborCostPerSquareFoot() + DELIMITER;
        lineEntry += order.getMaterialCost() + DELIMITER;
        lineEntry += order.getLaborCost() + DELIMITER;
        lineEntry += order.getTaxFinal() + DELIMITER;
        lineEntry += order.getTotalCost();

        return lineEntry;

    }

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

        String headerLine = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";
        out.println(headerLine);
        String itemList;

        for (Order currentOrder : allOrders) {
            // turn a inventory into a String
            itemList = marshallOrder(currentOrder);
            // write the inventory  object to the file
            out.println(itemList);
            // force PrintWriter to write line to the file

        }
        out.flush();
        out.close();

    }

    @Override
    public String exportAllData() throws FlooringMasteryDaoException {
        String exportFile = "export_orders.txt";
        File ordersFolder = new File("Orders/");
        File[] orderFiles = ordersFolder.listFiles();
        Scanner exportInput;
        PrintWriter exportOutput;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        try {
            exportOutput = new PrintWriter(new FileWriter(exportFile));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException("Error occured while writing to file", e);
        }

        for (File file : orderFiles) {
            String fileName = file.getName();
            String stringDate = fileName.substring(7, 15);
            LocalDate ordersDate = LocalDate.parse(stringDate, formatter);
            try {
                exportInput = new Scanner(new BufferedReader(new FileReader("Orders/" + fileName)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryDaoException("Could not find data file for date " + ordersDate, e);
            }
            if (exportInput.hasNextLine()) {
                exportOutput.println(ordersDate);
                exportOutput.println();
            } else {
                // avoid printing blank lines for files with no orders
                continue;
            }

            while (exportInput.hasNextLine()) {
                String orderRecord = exportInput.nextLine();
                exportOutput.println(orderRecord);
                exportOutput.flush();
            }

            exportOutput.println();

        }
        exportOutput.close();
        return exportFile;

    }

    @Override
    public Order addOrder(Order placeOrder) throws FlooringMasteryDaoException {
        int orderNumber = 1;

        try {
            List<Order> orders = getAllOrders(placeOrder.getOrderDate());

            if (!orders.isEmpty()) {
                int lastIndex = allOrders.size() - 1;
                // takes the highest number of orders and adds 1 to the order list
                orderNumber = allOrders.get(lastIndex).getOrderNumber() + 1;
            }

            placeOrder.setOrderNumber(orderNumber);

        } catch (FlooringMasteryDaoException e) {
            placeOrder.setOrderNumber(orderNumber);
        }

        allOrders.add(placeOrder);
        writeOrders(placeOrder.getOrderDate());
        return placeOrder;

    }

    // edit single order
    @Override
    public Order editOrder(Order editOrder) throws FlooringMasteryDaoException {
        int orderNumber = editOrder.getOrderNumber();
        loadOrders(editOrder.getOrderDate());

        for (int i = 0; i < allOrders.size(); i++) {
            // goes through array list of orders
            if (allOrders.get(i).getOrderNumber() == editOrder.getOrderNumber()) {
                allOrders.set(i, editOrder);
                writeOrders(editOrder.getOrderDate());
                return editOrder;
            }

        }

        return null;

    }

    //remove order
    @Override
    public Order removeOrder(Order removeOrder) throws FlooringMasteryDaoException {

        int orderNumber = removeOrder.getOrderNumber();
        LocalDate orderDate = removeOrder.getOrderDate();
        loadOrders(orderDate);
        int indexOrder;
        List<Order> newOrderList = new ArrayList<>();
        for (Order order : allOrders) {

            if (!(removeOrder.getOrderNumber() == order.getOrderNumber())) {
                newOrderList.add(order);
            }
        }
        this.allOrders = newOrderList;
        //locating order number in list

        writeOrders(orderDate);

        if (this.allOrders.isEmpty()) {
            deleteEmptyFile(orderDate);
        }

        return removeOrder;

    }
    
    //to delete files without any orders
    private void deleteEmptyFile(LocalDate date) {
        String fileName;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = date.format(formatter);
        fileName = "Orders/Orders_" + dateString + ".txt";

        File fileToDelete = new File(fileName);
        fileToDelete.delete();
    }
    
    // to display existing dates before selection
    @Override
    public List<String> getExistingDates() {
        List<String> orderDates = new ArrayList<>();
        File ordersFolder = new File("Orders/");
        File[] orderFiles = ordersFolder.listFiles();

        for (File file : orderFiles) {
            String fileName = file.getName();
            String stringDate = fileName.substring(7, 15);
            orderDates.add(stringDate);
        }
        return orderDates;
    }
}
