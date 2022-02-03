/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author ravee
 */
public class Order {

    // constructor to create an order
    int orderNumber;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.orderNumber;
        hash = 83 * hash + Objects.hashCode(this.customerName);
        hash = 83 * hash + Objects.hashCode(this.state);
        hash = 83 * hash + Objects.hashCode(this.taxRate);
        hash = 83 * hash + Objects.hashCode(this.productType);
        hash = 83 * hash + Objects.hashCode(this.area);
        hash = 83 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 83 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        hash = 83 * hash + Objects.hashCode(this.materialCost);
        hash = 83 * hash + Objects.hashCode(this.laborCost);
        hash = 83 * hash + Objects.hashCode(this.taxFinal);
        hash = 83 * hash + Objects.hashCode(this.totalCost);
        hash = 83 * hash + Objects.hashCode(this.orderDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.taxFinal, other.taxFinal)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        return true;
    }
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
        this.area = area.setScale(2, RoundingMode.HALF_UP);
        this.costPerSquareFoot = costPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
        this.laborCostPerSquareFoot = laborCostPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
        this.materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);
        this.laborCost = laborCost.setScale(2, RoundingMode.HALF_UP);
        this.taxFinal = taxFinal.setScale(2, RoundingMode.HALF_UP);
        this.totalCost = totalCost.setScale(2, RoundingMode.HALF_UP);
        this.orderDate = orderDate;
    }

    public BigDecimal getArea() {
        return area;
    }

    public Order(String customerName, String state, String productType, BigDecimal area, LocalDate orderDate) {

        this.orderDate = orderDate;
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.area = area.setScale(2, RoundingMode.HALF_UP);


    }
//options:
    // 1. secondary overloaded constructor will have the values of the costs calculated here
    // 2. calculations in the service layer, set to object from the service layer

    //Setters are for Service layer
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot.setScale(2, RoundingMode.HALF_UP);;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot.setScale(2, RoundingMode.HALF_UP);;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost.setScale(2, RoundingMode.HALF_UP);;
    }

    public void setTaxFinal(BigDecimal taxFinal) {
        this.taxFinal = taxFinal.setScale(2, RoundingMode.HALF_UP);;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost.setScale(2, RoundingMode.HALF_UP);;
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

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setArea(BigDecimal area) {
        this.area = area.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborCost() {
        return laborCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTaxFinal() {
        return taxFinal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalCost() {
        return totalCost.setScale(2, RoundingMode.HALF_UP);
    }

}
