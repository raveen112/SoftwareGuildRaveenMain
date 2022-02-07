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
  
    private final String FILE_NAME_PREFIX;
    // 5. marshall
    // 2. unmarshall
    // 3. getAllOrders
    // 8. removeOrder
    // 4. writeOrder
    // 6. addOrder
    // 7. editOrder
    // 1. loadOrders

    public FlooringMasteryDaoFileImpl() {
        FILE_NAME_PREFIX = "Orders/Order_";
    }

    public FlooringMasteryDaoFileImpl(String prefixFileName) {
        FILE_NAME_PREFIX = prefixFileName;

    }

    // Unmarshall from txt file 
    private Order unmarshallOrder(String orderString, LocalDate dateStamp) {
        
        //validation for "," and "."
        final int NUM_FIELDS = 12;
        String[] fieldArray = orderString.split(DELIMITER);
        int lastIndexOfName = fieldArray.length - NUM_FIELDS + 1;
        int index = 0;
        int orderNumber = Integer.parseInt(fieldArray[index++]);
        String customerName = "";
        do {
            if (index == lastIndexOfName) {
                customerName += fieldArray[index++];
            } else {
                customerName += fieldArray[index++] + DELIMITER;
            }
        } while (index <= lastIndexOfName);
        String state = fieldArray[index++];
        BigDecimal taxRate = new BigDecimal(fieldArray[index++]).setScale(2, RoundingMode.HALF_UP);
        String productType = fieldArray[index++];
        BigDecimal area = new BigDecimal(fieldArray[index++]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal costPerSquareFoot = new BigDecimal(fieldArray[index++]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(fieldArray[index++]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal materialCost = new BigDecimal(fieldArray[index++]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCost = new BigDecimal(fieldArray[index++]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxFinal = new BigDecimal(fieldArray[index++]).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalCost = new BigDecimal(fieldArray[index]).setScale(2, RoundingMode.HALF_UP);

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
        String fileName = FILE_NAME_PREFIX + dateString + ".txt";
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
        fileName = FILE_NAME_PREFIX + dateString + ".txt";
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
            String stringDate = fileName.substring(6, 14);
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
        // loads into a new array 
        List<Order> newOrderList = new ArrayList<>();
        for (Order order : allOrders) {

            if (!(orderNumber == order.getOrderNumber())) {
                newOrderList.add(order);
            }
        }
        this.allOrders = newOrderList;
        //locating order number in list

        writeOrders(orderDate);
        // deletes file if empty after removing contents before header
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
        fileName = FILE_NAME_PREFIX + dateString + ".txt";

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
            String stringDate = fileName.substring(6, 14);
            orderDates.add(stringDate);
        }
        return orderDates;
    }
}
