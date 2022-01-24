/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.controller;

import com.raveenm.flooringmastery.dao.FlooringMasteryDaoException;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.raveenm.flooringmastery.dto.Order;
import com.raveenm.flooringmastery.ui.UserIO;
import com.raveenm.flooringmastery.ui.UserIOConsoleImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class FlooringMasteryController {

    private UserIO io = new UserIOConsoleImpl();
    FlooringMasteryDaoFileImpl dao = new FlooringMasteryDaoFileImpl();

    public void run() throws FlooringMasteryDaoException {

        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            io.print("<< Flooring Program >>");
            io.print("1. Display Orders");
            io.print("2. Add an Order");
            io.print("3. Edit an Order");
            io.print("4. Remove an Order");
            io.print("5. Export All Data");
            io.print("6. Exit");

            menuSelection = io.readInt("Please select from the"
                    + " above choices.", 1, 5);

            switch (menuSelection) {
                case 1:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter a date to see orders (MMddyyyy): ");
                    LocalDate queryDate = LocalDate.parse(sc.nextLine(), formatter);

                    List<Order> testOrders = dao.getAllOrders(queryDate);

                    for (Order order : testOrders) {
                        System.out.println(order.getOrderNumber() + " " + order.getCustomerName() + " " + order.getProductType());
                    }

            
            break;

        
    

    case 2:
                    io.print("");
                    break;
                case 3:
                    io.print("");
                    break;
                case 4:
                    io.print("");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
    io.print("GOOD BYE");
    
}}

    

