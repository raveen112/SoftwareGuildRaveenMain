/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.service;

import com.raveenm.flooringmastery.dao.FlooringMasteryDaoException;
import com.raveenm.flooringmastery.dao.OrderPersistenceException;
import com.raveenm.flooringmastery.dto.Order;
import com.raveenm.flooringmastery.dto.Product;
import com.raveenm.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface FlooringMasteryService {

    //OrderDao methods
    List<Order> getAllOrders(LocalDate queryDate) throws FlooringMasteryDaoException, OrdersNotFoundException;
    Order addOrder(Order placeOrder) throws FlooringMasteryDaoException;
    Order editOrder(Order editOrder) throws FlooringMasteryDaoException;
    Order removeOrder(Order removeOrder) throws FlooringMasteryDaoException;

   //TaxDao methods
    Tax getStateTax(String stateAbbreviation)throws OrderPersistenceException;
    List<Tax> getAllStateTaxes()throws OrderPersistenceException;
    
    //ProductDao 
    List<Product> getallProductTypes()throws OrderPersistenceException;
    Product getProductType(String productType)throws OrderPersistenceException;
    
    Order getOrderSummary(Order customerOrderFinal) throws InvalidDateException, InvalidCustomerNameException, StateNotFoundException, ProductNotFoundException, InsufficientSquareFootageException, OrderPersistenceException;
}
