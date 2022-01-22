/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author ravee
 */
public class Order {

    // constructor to create an order
    int orderNumber;
    String customerName;
    String state;
    BigDecimal taxRate;
    String productType;
    BigDecimal costPerSquareFoot;
    BigDecimal laborCostPerSquareFoot;
    BigDecimal materialCost;
    BigDecimal laborCost;
    BigDecimal taxFinal;
    BigDecimal totalCost;

    public Order(int orderNumber,
            String customerName,
            String state,
            BigDecimal taxRate,
            String productType,
            BigDecimal costPerSquareFoot,
            BigDecimal laborCostPerSquareFoot,
            BigDecimal materialCost,
            BigDecimal laborCost,
            BigDecimal taxFinal,
            BigDecimal totalCost) {

        
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate ;
        this.productType = productType;
        this.costPerSquareFoot =costPerSquareFoot ;
        this.laborCostPerSquareFoot =laborCostPerSquareFoot ;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.taxFinal = taxFinal;
        this.totalCost = totalCost;
    }
    
    //options:
    // 1. secondary overloaded constructor will have the values of the costs calculated here
    // 2. calculations in the service layer, set to object from the service layer
    

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTaxFinal() {
        return taxFinal;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

}
