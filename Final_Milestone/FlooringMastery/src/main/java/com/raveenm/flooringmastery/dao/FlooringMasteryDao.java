
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;




/**
 *
 * @author ravee
 */
public interface FlooringMasteryDao {
   public List<Order> getAllOrders(LocalDate queryDate) throws FlooringMasteryDaoException;
   public Order addOrder(Order placeOrder);
}
