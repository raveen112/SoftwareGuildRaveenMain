package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface FlooringMasteryDao {

    List<Order> getAllOrders(LocalDate queryDate) throws FlooringMasteryDaoException;
    
    Order getSingleOrder(int orderID) throws FlooringMasteryDaoException;

    Order addOrder(Order placeOrder) throws FlooringMasteryDaoException;

    Order editOrder(Order editOrder) throws FlooringMasteryDaoException;

    Order removeOrder(Order removeOrder) throws FlooringMasteryDaoException;
    
    String exportAllData() throws FlooringMasteryDaoException; 

    List<String> getExistingDates();
}
