/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author ravee
 */
public class Inventory {
    
    private BigDecimal itemCost;
    private String itemName;
    private int itemCount;
    private String id;
    
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