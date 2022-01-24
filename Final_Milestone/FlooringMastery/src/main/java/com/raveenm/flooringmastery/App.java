/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery;

import com.raveenm.flooringmastery.dao.FlooringMasteryDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoException;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.raveenm.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class App {
    
    
    public static void main(String[] args) throws FlooringMasteryDaoException {
        
        FlooringMasteryDaoFileImpl dao = new FlooringMasteryDaoFileImpl();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        Scanner sc  = new Scanner(System.in);
        System.out.println("Enter a date to see orders (MMddyyyy): ");
        LocalDate queryDate = LocalDate.parse(sc.nextLine(), formatter);
        
        List<Order> testOrders = dao.getAllOrders(queryDate);
        
        for(Order order: testOrders){
            System.out.println( order.getOrderNumber()+" "+  order.getCustomerName()+" "+ order.getProductType());
        } 
                
    }
}
