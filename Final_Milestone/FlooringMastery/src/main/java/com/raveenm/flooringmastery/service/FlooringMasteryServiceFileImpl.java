/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.service;

import com.raveenm.flooringmastery.dao.FlooringMasteryDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoException;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDaoFileImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl;
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
public class FlooringMasteryServiceFileImpl implements FlooringMasteryService  {

    FlooringMasteryDao dao;
    FlooringMasteryTaxDao taxDao;
    FlooringMasteryProductDao productDao;

    public FlooringMasteryServiceFileImpl(FlooringMasteryDaoFileImpl dao, FlooringMasteryTaxDaoFileImpl taxDao, FlooringMasteryProductDaoFileImpl productDao) {
        this.dao = dao;
        this.taxDao = taxDao;
        this.productDao = productDao;
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
    private void validateSquareFootage(Order customerOrder) throws InsufficientSquareFootageException, OrderPersistenceException{
        BigDecimal orderArea = customerOrder.getArea();
        BigDecimal lowerLimit = new BigDecimal("100");
        if (orderArea.compareTo(lowerLimit) == -1) {
            throw new InsufficientSquareFootageException("Square footage needs to be over 100 sq. feet");

        }

       
    }
    
    //validate future date (readFutureLocalDate) = add to IO as well

    // private void validateCustomerName(Order customerOrder) throws  
    @Override
    public Order getOrderSummary(Order customerOrderFinal) throws StateNotFoundException, ProductNotFoundException, InsufficientSquareFootageException, OrderPersistenceException {
        // call getter methods for all the calculations
        validateSquareFootage(customerOrderFinal);
        validateProductType(customerOrderFinal);
        validateStateTaxes(customerOrderFinal);

        Product orderProduct = productDao.getProduct(customerOrderFinal.getProductType());
        Tax orderTax = taxDao.getStateTax(customerOrderFinal.getState());
        customerOrderFinal.setMaterialCost(calculateMaterialCost(customerOrderFinal, orderProduct));
        customerOrderFinal.setLaborCost(calculateLaborCost(customerOrderFinal, orderProduct));
        customerOrderFinal.setTaxRate(orderTax.getRawTax());
        customerOrderFinal.setTaxFinal(calculateTax(customerOrderFinal));
        customerOrderFinal.setTotalCost(calculateTotalCost(customerOrderFinal));

        // add return 
        return customerOrderFinal;
    }

    private BigDecimal calculateMaterialCost(Order order, Product product) {

        return order.getArea().multiply(product.getCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);

    }

    private BigDecimal calculateLaborCost(Order order, Product product) {
        return order.getArea().multiply(product.getLaborCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTax(Order order) {
        BigDecimal materialLaborSum = order.getMaterialCost().add(order.getLaborCost());
        BigDecimal taxRate = order.getTaxRate();
        BigDecimal finalTax = materialLaborSum.add(taxRate);

        return finalTax;
    }

    private BigDecimal calculateTotalCost(Order order) {
        return order.getMaterialCost().add(order.getLaborCost().add(order.getTaxFinal()));
    }

    // Pass through methods 
    @Override
    public List<Order> getAllOrders(LocalDate queryDate) throws FlooringMasteryDaoException {
        return dao.getAllOrders(queryDate);
    }

    @Override
    public Order addOrder(Order placeOrder) {
        return dao.addOrder(placeOrder);
    }

    @Override
    public Tax getStateTax(String stateAbbreviation) throws OrderPersistenceException {
        return taxDao.getStateTax(stateAbbreviation);
    }

    @Override
    public List<Tax> getAllStateTaxes()throws OrderPersistenceException{
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

}
