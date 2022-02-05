/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author ravee
 */
public class FlooringMasteryOrderDaoStubImpl implements FlooringMasteryDao {

    Order order1 = new Order(1, "Yahoo Answers", "WA", new BigDecimal("9.25"), "Wood", new BigDecimal("3712.00"), new BigDecimal("5.15"), new BigDecimal("4.75"),
            new BigDecimal("19116.80"), new BigDecimal("17632.00"), new BigDecimal("3399.26"), new BigDecimal("40148.06"), LocalDate.now().plusDays(1));
    Order order2 = new Order(2, "Justin Timberu", "GA", new BigDecimal("6.00"), "Carpet", new BigDecimal("2143.00"), new BigDecimal("2.25"), new BigDecimal("2.10"),
            new BigDecimal("4821.75"), new BigDecimal("4500.00"), new BigDecimal("559.32"), new BigDecimal("9881.37"), LocalDate.now().plusDays(1));

    @Override
    public List<Order> getAllOrders(LocalDate queryDate) throws FlooringMasteryDaoException {
        List<Order> allOrders = new ArrayList<>();
        allOrders.add(order1);
        allOrders.add(order2);

        return allOrders;

    }


    @Override
    public Order addOrder(Order placeOrder) throws FlooringMasteryDaoException {

        return placeOrder;
    }

    @Override
    public Order editOrder(Order editOrder) throws FlooringMasteryDaoException {
        return editOrder;
    }

    @Override
    public Order removeOrder(Order removeOrder) throws FlooringMasteryDaoException {
      return removeOrder;
    }

    //dont need to test
    @Override
    public String exportAllData() throws FlooringMasteryDaoException {
        return null;
    }

    @Override
    public List<String> getExistingDates() {
       List<String> dates = new ArrayList<>();
       dates.add("02282022");
       
       return dates;
    }

}
