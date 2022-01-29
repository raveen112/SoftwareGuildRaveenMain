/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.ui;

import com.raveenm.flooringmastery.dto.Order;
import com.raveenm.flooringmastery.dto.Product;
import com.raveenm.flooringmastery.dto.Tax;
import com.raveenm.flooringmastery.service.FlooringMasteryService;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class FlooringMasteryView {

    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public void print(String prompt) {
        io.print(prompt);
    }

    public int menuSelection() {
        io.print("<< Flooring Program >>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Exit");

        return io.readInt("Enter your choice: ", 1, 6);

    }

    public LocalDate getOrderDate() {
        LocalDate queryDate = io.readLocalDate("Enter a date to see orders (MMddyyyy): ");
        return queryDate;
    }
    
    public LocalDate getFutureOrderDate(String prompt){
        LocalDate queryDate = io.readFutureLocalDate(prompt);
        return queryDate;
    }

    public void displayOrders(List<Order> orders) {
        // print banner
        for (Order order : orders) {
            print("Order Number: " + order.getOrderNumber());
            print("Customer Name: " + order.getCustomerName());
            print("State: " + order.getState());
            print("Area: " + order.getArea());
            print("Material Cost: " + order.getMaterialCost());
            print("Product Type: " + order.getProductType());
            print("Labor Cost: " + order.getLaborCost());
            print("Labor Cost Per Sq. FT: " + order.getLaborCostPerSquareFoot());
            print("Tax amount: " + order.getTaxFinal());
            print("Tax rate: " + order.getTaxRate());
            print("Cost Per Sq FT: " + order.getCostPerSquareFoot());
            print("TOTAL AMOUNT DUE: " + order.getTotalCost());
        }

    }

    // pre validation for user name(chars, num and strings) and area(100sqft)
    // state and material list for validation of existing state and products
    public Order getOrderDetails(List<Product> products, List<Tax> stateTax) {

        LocalDate orderDate = io.readFutureLocalDate("Enter future date to add order: ");
        String customerName = io.readString("Enter customer name: ");
        String stateName = io.readString("Enter State Abbreviation: ");
        String productType = io.readString("Enter product type: ");
        BigDecimal area = io.readBigDecimal("Enter Area (min 100sqft: ");

        return new Order(customerName, stateName, productType, area, orderDate);
    }

    public void printOrderSummary(Order order) {

        print("Order Number: " + order.getOrderNumber());
        print("Customer Name: " + order.getCustomerName());
        print("State: " + order.getState());
        print("Area: " + order.getArea());
        print("Material Cost: " + order.getMaterialCost());
        print("Product Type: " + order.getProductType());
        print("Labor Cost: " + order.getLaborCost());
        print("Labor Cost Per Sq. FT: " + order.getLaborCostPerSquareFoot());
        print("Tax amount: " + order.getTaxFinal());
        print("Tax rate: " + order.getTaxRate());
        print("Cost Per Sq FT: " + order.getCostPerSquareFoot());
        print("TOTAL AMOUNT DUE: " + order.getTotalCost());
    }

    public boolean getConfirmation(String prompt) {
        String userAnswer = io.readString(prompt);
        if (userAnswer.equalsIgnoreCase("Y")) {
            return true;

        } else {
            return false;
        }

    }

    public Order getEditedOrderDetails(List<Order> orders) {
        Order orderToEdit = null;
        int orderNumberToEdit;

        while (orderToEdit == null) {
            orderNumberToEdit = io.readInt("Enter Order Number to Edit? ");
            for (Order editOrder : orders) {
                if (editOrder.getOrderNumber() == orderNumberToEdit) {
                    orderToEdit = editOrder;
                }
            }
        }
        print("Current Customer Name:"+ orderToEdit.getCustomerName());
        String editedCustomerName = io.readString("Enter New Name: ");
        if(editedCustomerName.isBlank()){
            editedCustomerName = orderToEdit.getCustomerName();
        }
        print("Current State: " + orderToEdit.getState());
        String editedState = io.readString("Enter New State: ");
        if(editedState.isBlank()){
            editedState = orderToEdit.getState();
        }
        print("Current Product Type: " + orderToEdit.getProductType());
        String editedProductType =  io.readString("Enter New Product Type: ");
        if(editedProductType.isBlank()){
            editedProductType = orderToEdit.getProductType();
        }
        
        print("Current Area: "+ orderToEdit.getArea());
        BigDecimal editedArea = io.readBigDecimalMin("Enter New Area: ", new BigDecimal("100"));
        
        orderToEdit.setCustomerName(editedCustomerName);
        orderToEdit.setState(editedState);
        orderToEdit.setProductType(editedProductType);
        orderToEdit.setArea(editedArea);
        
        return orderToEdit;
        
    }

   

}
