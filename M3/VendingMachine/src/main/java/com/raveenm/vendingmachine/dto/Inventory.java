/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author ravee
 */
public class Inventory {
    
    private BigDecimal itemCost;
    private String itemName;
    private int itemCount;
    private String id;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.itemCost);
        hash = 59 * hash + Objects.hashCode(this.itemName);
        hash = 59 * hash + this.itemCount;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Inventory other = (Inventory) obj;
        if (this.itemCount != other.itemCount) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.itemCost, other.itemCost)) {
            return false;
        }
        return true;
    }
    
    
    public Inventory(String id, String itemName, BigDecimal itemCost, int itemCount){
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemCount = itemCount;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }
    
    public String getItemName() {
        return itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    
}