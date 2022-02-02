/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.service;

import com.raveenm.flooringmastery.dao.FlooringMasteryDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoException;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDao;
import com.raveenm.flooringmastery.dao.OrderPersistenceException;
import com.raveenm.flooringmastery.dto.Order;
import com.raveenm.flooringmastery.dto.Product;
import com.raveenm.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author ravee
 */
public class FlooringMasteryServiceFileImpl implements FlooringMasteryService {

    FlooringMasteryDao dao;
    FlooringMasteryTaxDao taxDao;
    FlooringMasteryProductDao productDao;

    public FlooringMasteryServiceFileImpl(FlooringMasteryDao dao, FlooringMasteryTaxDao taxDao, FlooringMasteryProductDao productDao) {
        this.dao = dao;
        this.taxDao = taxDao;
        this.productDao = productDao;
    }

    public FlooringMasteryServiceFileImpl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // check for valid state abbreviations
    private void validateStateTaxes(Order customerOrder) throws StateNotFoundException, OrderPersistenceException {

        List<Tax> stateTaxes = getAllStateTaxes();

        //enhanced for loop to go through the List of states
        for (Tax stateTax : stateTaxes) {
            // use state abbreviation instead of statename
            if (customerOrder.getState().equals(stateTax.getStateAbbreviation())) {
                return;
            }

        }
        throw new StateNotFoundException("State not found in database! -_-");
    }

    // check for valid product type
    private void validateProductType(Order customerOrder) throws ProductNotFoundException, OrderPersistenceException {
        List<Product> productTypes = getallProductTypes();

        for (Product productType : productTypes) {
            if (customerOrder.getProductType().equals(productType.getProductType())) {
                return;
            }
        }
        throw new ProductNotFoundException("Product not found in the database! -_-");
    }

    // validate the square footage restraint (>100 sq. ft)
    private void validateSquareFootage(Order customerOrder) throws InsufficientSquareFootageException, OrderPersistenceException {
        BigDecimal orderArea = customerOrder.getArea();
        BigDecimal lowerLimit = new BigDecimal("100");
        if (orderArea.compareTo(lowerLimit) == -1) {
            throw new InsufficientSquareFootageException("Square footage needs to be over 100 sq. feet");

        }

    }

    //validate future date (readFutureLocalDate) = add to IO as well
    // private void validateCustomerName(Order customerOrder) throws 
    // only returns a valid customer name
    private boolean isValidCustomerName(Order customerNameOrder) {
        String customerName = customerNameOrder.getCustomerName().toUpperCase().strip();
        String[] allCharacters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            " ", ",", ".",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

        if (customerName.isBlank()) {
            return false;
        }

        for (int i = 0; i < customerName.length(); i++) {
            boolean isAMatch = false;

            for (int j = 0; j < allCharacters.length; j++) {

                if (allCharacters[j].charAt(0) == (customerName.charAt(i))) {
                    isAMatch = true;
                    break;
                }

            }
            if (!isAMatch) {
                return false;
            }
        }
        return true;
    }

    private void validateCustomerName(Order customerOrderName) throws InvalidCustomerNameException {
        String customerName = customerOrderName.getCustomerName();
        if (!isValidCustomerName(customerOrderName)) {
            throw new InvalidCustomerNameException("Please enter a valid customer name");
        }
    }

    private void validateDate(Order customerOrder) throws InvalidDateException{
        LocalDate currentDate = LocalDate.now();
        LocalDate orderDate = customerOrder.getOrderDate();
        
        if(!currentDate.isBefore(orderDate)){
            throw new InvalidDateException("Please enter a future date.");
        }
        
    }
    
    @Override
    public Order getOrderSummary(Order customerOrderFinal) throws InvalidDateException, InvalidCustomerNameException, StateNotFoundException, ProductNotFoundException, InsufficientSquareFootageException, OrderPersistenceException {
        // call getter methods for all the calculations
        validateSquareFootage(customerOrderFinal);
        validateProductType(customerOrderFinal);
        validateStateTaxes(customerOrderFinal);
        validateCustomerName(customerOrderFinal);
        validateDate(customerOrderFinal);

        Product orderProduct = productDao.getProduct(customerOrderFinal.getProductType());
        Tax orderTax = taxDao.getStateTax(customerOrderFinal.getState());
        customerOrderFinal.setLaborCostPerSquareFoot(orderProduct.getLaborCostPerSquareFoot());
        customerOrderFinal.setCostPerSquareFoot(orderProduct.getCostPerSquareFoot());
        customerOrderFinal.setMaterialCost(calculateMaterialCost(customerOrderFinal));
        customerOrderFinal.setLaborCost(calculateLaborCost(customerOrderFinal));
        customerOrderFinal.setTaxRate(orderTax.getRawTax());
        customerOrderFinal.setTaxFinal(calculateTax(customerOrderFinal));
        customerOrderFinal.setTotalCost(calculateTotalCost(customerOrderFinal));

        return customerOrderFinal;
    }

    private BigDecimal calculateMaterialCost(Order order) {

        return order.getArea().multiply(order.getCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);

    }

    private BigDecimal calculateLaborCost(Order order) {
        return order.getArea().multiply(order.getLaborCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTax(Order order) {
        BigDecimal materialLaborSum = order.getMaterialCost().add(order.getLaborCost());
        BigDecimal taxRate = order.getTaxRate().divide(new BigDecimal("100"));
        BigDecimal finalTax = materialLaborSum.multiply(taxRate);

        return finalTax;
    }

    private BigDecimal calculateTotalCost(Order order) {
        return order.getMaterialCost().add(order.getLaborCost().add(order.getTaxFinal()));
    }

    // Pass through methods 
    @Override
    public List<Order> getAllOrders(LocalDate queryDate) throws FlooringMasteryDaoException, OrdersNotFoundException {
        List<Order> orders = dao.getAllOrders(queryDate);
        if(orders.isEmpty()){
            throw new OrdersNotFoundException("Orders not found for this date.");
        }
        return orders;
    }

    @Override
    public Order addOrder(Order placeOrder) throws FlooringMasteryDaoException {
        return dao.addOrder(placeOrder);
    }

    @Override
    public Tax getStateTax(String stateAbbreviation) throws OrderPersistenceException {
        return taxDao.getStateTax(stateAbbreviation);
    }

    @Override
    public List<Tax> getAllStateTaxes() throws OrderPersistenceException {
        return taxDao.getAllStateTaxes();
    }

    @Override
    public List<Product> getallProductTypes() throws OrderPersistenceException {
        return productDao.getallProductTypes();
    }

    @Override
    public Product getProductType(String productType) throws OrderPersistenceException {
        return productDao.getProduct(productType);
    }

    @Override
    public Order editOrder(Order editOrder) throws FlooringMasteryDaoException {
        return dao.editOrder(editOrder);
    }

    @Override
    public Order removeOrder(Order removeOrder) throws FlooringMasteryDaoException {
        return dao.removeOrder(removeOrder);
    }
    
    @Override
    public String exportOrders() throws FlooringMasteryDaoException{
        return dao.exportAllData();
    }

    @Override
    public List<String> getExistingDates(){
        return dao.getExistingDates();
    }
}
