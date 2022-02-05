/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.ui;

import com.raveenm.flooringmastery.dto.Order;
import com.raveenm.flooringmastery.dto.Product;
import com.raveenm.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

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
        io.print("* << Flooring Program >>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Export All Data");
        io.print("* 6. Exit \n*");

        return io.readInt("Enter your choice: ", 1, 6);

    }

    public LocalDate getOrderDate() {
        LocalDate queryDate = io.readLocalDate("Enter a date to see orders (MMddyyyy): ");
        return queryDate;
    }

    public LocalDate getFutureOrderDate(String prompt) {
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

    public void displayStateList(List<Tax> stateTaxes) {
        String availStates = "Choose State: ";
        for (Tax stateTax : stateTaxes) {
            availStates += stateTax.getStateAbbreviation() + " ";
        }
        print(availStates);
    }

    public void displayProductList(List<Product> products) {
        String availProds = "Choose product: ";
        for (Product product : products) {
            availProds += product.getProductType() + " ";
        }
        print(availProds);
    }

    // pre validation for user name(chars, num and strings) and area(100sqft)
    // state and material list for validation of existing state and products
    public Order getOrderDetails(List<Product> products, List<Tax> stateTax) {

        LocalDate orderDate = io.readFutureLocalDate("Enter future date to add order: ");
        String customerName = io.readString("Enter customer name: ");

        // add, list available states.
        displayStateList(stateTax);
        String stateName = "";

        // validation with the text file
        boolean isValidState = false;
        while (!isValidState) {
            stateName = io.readString("Enter a valid state abbreviation:");
            for (Tax state : stateTax) {
                if (stateName.equalsIgnoreCase(state.getStateAbbreviation())) {
                    isValidState = true;
                    break;
                }

            }

        }
        stateName = stateName.toUpperCase();

        //validation for product list
        displayProductList(products);
        String productType = "";

        // validation with the text file
        boolean isValidProduct = false;
        while (!isValidProduct) {
            productType = io.readString("Enter a valid Product Type:");
            for (Product product : products) {
                if (productType.equalsIgnoreCase(product.getProductType())) {
                    isValidProduct = true;
                    break;
                }

            }

        }
        productType = productType.substring(0, 1).toUpperCase() + productType.substring(1).toLowerCase();

        //area
        BigDecimal area = io.readBigDecimal("Enter Area (min 100sqft): ");

        return new Order(customerName, stateName, productType, area, orderDate);
    }

    public void printOrderSummary(Order order) {

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
        if (userAnswer.equalsIgnoreCase("Yes")) {
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
        print("Current Customer Name:" + orderToEdit.getCustomerName());
        String editedCustomerName = io.readString("Enter New Name: ");
        if (editedCustomerName.isBlank()) {
            editedCustomerName = orderToEdit.getCustomerName();
        }
        print("Current State: " + orderToEdit.getState());
        String editedState = io.readString("Enter New State: ");
        if (editedState.isBlank()) {
            editedState = orderToEdit.getState();
        }
        print("Current Product Type: " + orderToEdit.getProductType());
        String editedProductType = io.readString("Enter New Product Type: ");
        if (editedProductType.isBlank()) {
            editedProductType = orderToEdit.getProductType();
        }

        print("Current Area: " + orderToEdit.getArea());
        BigDecimal editedArea = io.readBigDecimalMin("Enter New Area: ", new BigDecimal("100"));

        orderToEdit.setCustomerName(editedCustomerName);
        orderToEdit.setState(editedState);
        orderToEdit.setProductType(editedProductType);
        orderToEdit.setArea(editedArea);

        return orderToEdit;

    }

    public Order getOrderToRemove(List<Order> orders) {
        Order orderToRemove = null;
        int orderNumberToRemove;

        while (orderToRemove == null) {
            orderNumberToRemove = io.readInt("Enter Order Number to Remove? ");
            for (Order removeOrder : orders) {
                if (removeOrder.getOrderNumber() == orderNumberToRemove) {

                    return removeOrder;
                }
            }
        }
        return orderToRemove;
    }

    public void displayExistingDates(List<String> existingDates) {
        if (existingDates.isEmpty()) {
            print("No Orders Exist!");

        } else {
            print("Dates: ");
            for (String existingDate : existingDates) {

                print(existingDate);

            }
        }
    }

    //Banner methods
    public void displayDashesBanner() {
        print("<><><><><><><><><><><><><><><><>");
    }

    public void successfullyDisplayedAll() {
        print("\n All Orders Are Displayed!");
        print("<><><><><><><><><><><><><><><><><><><><>");
    }

    public void displaySuccessfullyPlacedBanner(int orderNumber) {
        print("\n Successfully placed order #" + orderNumber);
        print("<><><><><><><><><><><><><><><><><><><><>");
    }

    public void displaySuccessfullyRemovedBanner(int removedNumber) {
        print("\n Successfully Removed Order " +removedNumber +" !");
        print("<><><><><><><><><><><><><><><><><><><><>");
    }

    public void displaySuccessfullyEditedBanner() {
        print("\n Successfully Edited Order!");
        print("<><><><><><><><><><><><><><><><><><><><>");
    }

}
