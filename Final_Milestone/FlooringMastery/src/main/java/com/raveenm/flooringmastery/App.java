/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery;

import com.raveenm.flooringmastery.dao.FlooringMasteryDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoException;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDaoFileImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl;
import com.raveenm.flooringmastery.dao.OrderPersistenceException;
import com.raveenm.flooringmastery.dto.Order;
import com.raveenm.flooringmastery.service.FlooringMasteryService;
import com.raveenm.flooringmastery.service.FlooringMasteryServiceFileImpl;
import com.raveenm.flooringmastery.service.InsufficientSquareFootageException;
import com.raveenm.flooringmastery.service.InvalidCustomerNameException;
import com.raveenm.flooringmastery.service.InvalidDateException;
import com.raveenm.flooringmastery.service.ProductNotFoundException;
import com.raveenm.flooringmastery.service.StateNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ravee
 */
public class App {

    public static void main(String[] args) throws FlooringMasteryDaoException, StateNotFoundException, ProductNotFoundException, InsufficientSquareFootageException, OrderPersistenceException, InvalidCustomerNameException, InvalidDateException {

        FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoFileImpl();
        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoFileImpl();
        FlooringMasteryService service = new FlooringMasteryServiceFileImpl(dao, taxDao, productDao);

        String name = "Acme, Inc.";
        String state = "CA";
        String product = "Tile";
        BigDecimal area = new BigDecimal("249").setScale(2, RoundingMode.UP);
                
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse("01272022", formatter);
                
        Order testOrder = new Order(name, state, product, area, date);
        Order processedOrder = service.getOrderSummary(testOrder);
        Order addOrder = service.addOrder(processedOrder);
        
        
        
        
        
//        String state1 = "TX";
//        String product1 = "Wood";
//        BigDecimal area1 = new BigDecimal("653").setScale(2, RoundingMode.UP);
//        addOrder.setState(state1);
//        addOrder.setArea(area1);
//        addOrder.setProductType(product1);
//        Order processedOrder2 = service.getOrderSummary(addOrder);
//        service.editOrder(processedOrder2);
        
        
        

    }
}
