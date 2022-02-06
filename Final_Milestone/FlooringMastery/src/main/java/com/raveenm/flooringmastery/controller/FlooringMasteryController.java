/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.controller;

import com.raveenm.flooringmastery.dao.FlooringMasteryDaoException;
import com.raveenm.flooringmastery.dao.OrderPersistenceException;
import com.raveenm.flooringmastery.dto.Order;
import com.raveenm.flooringmastery.dto.Product;
import com.raveenm.flooringmastery.dto.Tax;
import com.raveenm.flooringmastery.service.FlooringMasteryService;
import com.raveenm.flooringmastery.service.InsufficientSquareFootageException;
import com.raveenm.flooringmastery.service.InvalidCustomerNameException;
import com.raveenm.flooringmastery.service.InvalidDateException;
import com.raveenm.flooringmastery.service.OrdersNotFoundException;
import com.raveenm.flooringmastery.service.ProductNotFoundException;
import com.raveenm.flooringmastery.service.StateNotFoundException;
import com.raveenm.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ravee
 */
public class FlooringMasteryController {

//    FlooringMasteryDaoFileImpl dao = new FlooringMasteryDaoFileImpl();
//    FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoFileImpl();
//    FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoFileImpl();
    FlooringMasteryView view;
    private FlooringMasteryService service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        while (keepGoing) {
            try {
                // Initialized here since the tax and product files are immutable 
                List<Product> products = service.getallProductTypes();
                List<Tax> stateTax = service.getAllStateTaxes();
                List<String> orderDatesExisting;

                menuSelection = view.menuSelection();

                switch (menuSelection) {

                    //display orders
                    case 1:
                        displayAllOrders();
                        break;

                    // add order
                    case 2:

                        addOrder();
                        break;
                    // edit order        
                    case 3:
                        editOrder();
                        break;

                    // remove order      
                    case 4:
                        removeOrder();
                        break;

                    // export all data;
                    case 5:
                        exportOrders();
                        break;
                        
                    case 6:
                        keepGoing = false;
                        exitMessage();

                }

            } catch (FlooringMasteryDaoException | OrderPersistenceException | InvalidDateException | InvalidCustomerNameException | StateNotFoundException | ProductNotFoundException | InsufficientSquareFootageException | OrdersNotFoundException e) {
                view.print(e.getMessage());
            }

        }

    }

    private void exportOrders() throws FlooringMasteryDaoException {
        service.exportOrders();
    }

    private void displayAllOrders() throws FlooringMasteryDaoException, OrdersNotFoundException {

        List<String> orderDatesExisting;
        view.displayDashesBanner();
        orderDatesExisting = service.getExistingDates();
        view.displayExistingDates(orderDatesExisting);
        LocalDate queryDate = view.getOrderDate();
        view.displayOrders(service.getAllOrders(queryDate));
        view.successfullyDisplayedAll();

    }

    public void addOrder() throws OrderPersistenceException, StateNotFoundException,
            ProductNotFoundException, InsufficientSquareFootageException, InvalidDateException,
            FlooringMasteryDaoException, InvalidCustomerNameException {

        List<Product> products = service.getallProductTypes();
        List<Tax> stateTax = service.getAllStateTaxes();
        Order orderToAdd = view.getOrderDetails(products, stateTax);
        Order finalOrder = service.getOrderSummary(orderToAdd);
        view.displayDashesBanner();
        view.printOrderSummary(finalOrder);
        view.displayDashesBanner();

        if (view.getConfirmation("Are you sure you want to place the order?")) {
            Order addedOrder = service.addOrder(finalOrder);
            view.displaySuccessfullyPlacedBanner(addedOrder.getOrderNumber());
        }
    }

    public void editOrder() throws FlooringMasteryDaoException, InvalidDateException,
            InvalidCustomerNameException,
            StateNotFoundException, ProductNotFoundException, InsufficientSquareFootageException,
            OrderPersistenceException, OrdersNotFoundException {

        List<Product> products = service.getallProductTypes();
        List<Tax> stateTax = service.getAllStateTaxes();
        List<String> orderDatesExisting;
        view.displayDashesBanner();

        orderDatesExisting = service.getExistingDates();
        view.displayExistingDates(orderDatesExisting);
        LocalDate orderDate = view.getFutureOrderDate("Enter a future (post today) date: ");
        List<Order> allOrders = service.getAllOrders(orderDate);

        Order orderToEdit = view.getEditedOrderDetails(allOrders);
        orderToEdit = service.getOrderSummary(orderToEdit);
        view.displayDashesBanner();
        view.printOrderSummary(orderToEdit);
        view.displayDashesBanner();
        if (view.getConfirmation("Are you sure you want to EDIT this order?")) {
            service.editOrder(orderToEdit);
            view.displaySuccessfullyEditedBanner();
        }
    }

    public void removeOrder() throws OrderPersistenceException, FlooringMasteryDaoException, OrdersNotFoundException {

        List<Product> products = service.getallProductTypes();
        List<Tax> stateTax = service.getAllStateTaxes();
        List<String> orderDatesExisting;

        orderDatesExisting = service.getExistingDates();
        view.displayExistingDates(orderDatesExisting);

        LocalDate orderToRemoveDate = view.getOrderDate();
        List<Order> allOrders = service.getAllOrders(orderToRemoveDate);

        Order orderToRemove = view.getOrderToRemove(allOrders);
        
        view.displayDashesBanner();
        view.printOrderSummary(orderToRemove);
        view.displayDashesBanner();
        if (view.getConfirmation("Are you sure you want to REMOVE this order?")) {
            service.removeOrder(orderToRemove);
            view.displaySuccessfullyRemovedBanner(orderToRemove.getOrderNumber());
        }
    }

    private void exitMessage() {
        view.displayDashesBanner();
        view.print("\n Thank you! Have a good day ahead!");
    }

}
