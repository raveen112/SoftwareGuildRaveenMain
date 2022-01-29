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
import com.raveenm.flooringmastery.ui.UserIOConsoleImpl;
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

                menuSelection = view.menuSelection();

                switch (menuSelection) {
                    //display orders
                    case 1:
                        LocalDate queryDate = view.getOrderDate();
                        view.displayOrders(service.getAllOrders(queryDate));
                        break;

                    // add order
                    case 2:
                        Order orderToAdd = view.getOrderDetails(products, stateTax);
                        Order finalOrder = service.getOrderSummary(orderToAdd);
                        view.printOrderSummary(finalOrder);
                        if (view.getConfirmation("Are you sure you want to place the order?")) {
                            service.addOrder(finalOrder);
                        }

                        break;
                    // edit order        
                    case 3:
                        LocalDate orderDate = view.getFutureOrderDate("Enter a future (post today) date: ");
                        List<Order> allOrders = service.getAllOrders(orderDate);

                        Order orderToEdit = view.getEditedOrderDetails(allOrders);
                        orderToEdit = service.getOrderSummary(orderToEdit);
                        view.printOrderSummary(orderToEdit);
                        if (view.getConfirmation("Are you sure you want to edit this order?")) {
                            service.editOrder(orderToEdit);
                        }
                            break;
                            // remove order    
                        
                case 4:
                    
                        break;
                // export all data;
                case 5:
                        keepGoing = false;
                        break;
                default:
                        keepGoing = false;
            }

        }catch(FlooringMasteryDaoException | OrderPersistenceException | InvalidDateException | InvalidCustomerNameException |StateNotFoundException| ProductNotFoundException | InsufficientSquareFootageException | OrdersNotFoundException e){
            view.print(e.getMessage());
        }

            }

        }
    



    }
//     public Order editOrder(Order order, Tax tax, Product product ){
//        Order editedOrder;
//        
//        view.getOrderDate();
//        
//        // add validation to find order() and throw OrderNotFoundException/ NoOrdersOnDateException
//        
//        //prompt to find editable fields in order:
//        String oldCustomerName = order.getCustomerName();
//        String newCustomerName = getNewCustomer(oldCustomerName);
//        Product oldProductType = order.getProductType();
//    }

//    private String getNewCustomer(String currentName) {
//        String newCustomerName;
//
//        newCustomerName = view.promptCustomerName(currentName);
//        if (newCustomerName.isEmpty()) {
//            return currentName;
//        }
//        return newCustomerName;
    

