/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 *
 * @author ravee
 */
public enum Funds {

    ONE_DOLLAR(new BigDecimal("1.00").setScale(2, RoundingMode.UP), "$1"),
    TWENTY_FIVE_CENTS(new BigDecimal("0.25").setScale(2, RoundingMode.UP), "25c"),
    TEN_CENTS(new BigDecimal("0.10").setScale(2, RoundingMode.UP), "10c"),
    FIVE_CENTS(new BigDecimal("0.05").setScale(2, RoundingMode.UP), "5c"),
    ONE_CENT(new BigDecimal("0.01").setScale(2, RoundingMode.UP), "1c");
    
    private BigDecimal VALUE;
    private String NAME;

   

    Funds(BigDecimal VALUE, String NAME) {
        this.VALUE = VALUE;
        this.NAME = NAME;
    }

    public BigDecimal getVALUE() {
        return this.VALUE;
    }

    public String getNAME() {
        return this.NAME;
    }

}
