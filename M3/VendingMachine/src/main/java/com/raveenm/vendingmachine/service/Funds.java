/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.service;

import java.math.BigDecimal;
/**
 *
 * @author ravee
 */
public enum Funds {

    TWO_DOLLARS(new BigDecimal("2.00"), "$2"),
    ONE_DOLLAR(new BigDecimal("1.00"), "$1"),
    FIFTY_CENTS(new BigDecimal(".50"), "50c"),
    TWENTY_FIVE_CENTS(new BigDecimal(".25"), ".25c");

    private BigDecimal VALUE;
    private String NAME;

    Funds(BigDecimal VALUE, String NAME) {
        this.VALUE = VALUE;
        this.NAME = NAME;
    }

    public static BigDecimal getVALUE() {
        return Funds.getVALUE();
    }

    public String getNAME() {
        return NAME;
    }

}
