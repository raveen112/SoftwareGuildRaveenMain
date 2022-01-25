/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    BigDecimal area;
    BigDecimal costPerSquareFoot;
    BigDecimal laborCostPerSquareFoot;
    BigDecimal materialCost;
    BigDecimal laborCost;
    BigDecimal taxFinal;
    BigDecimal totalCost;
    LocalDate orderDate;

    public Order(int orderNumber,
            String customerName,
            String state,
            BigDecimal taxRate,
            String productType,
            BigDecimal area,
            BigDecimal costPerSquareFoot,
            BigDecimal laborCostPerSquareFoot ,
            BigDecimal materialCost ,
            BigDecimal laborCost ,
            BigDecimal taxFinal ,
            BigDecimal totalCost ,
            LocalDate orderDate) {

        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.taxFinal = taxFinal;
        this.totalCost = totalCost;
        this.orderDate = orderDate;
    }

    public BigDecimal getArea() {
        return area;
    }

    public Order(String customerName, String state, BigDecimal taxRate, String productType, BigDecimal area, LocalDate orderDate) {

        this.orderDate = orderDate;
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.area = area;


    }
//options:
    // 1. secondary overloaded constructor will have the values of the costs calculated here
    // 2. calculations in the service layer, set to object from the service layer

    //Setters are for Service layer
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public void setTaxFinal(BigDecimal taxFinal) {
        this.taxFinal = taxFinal;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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
