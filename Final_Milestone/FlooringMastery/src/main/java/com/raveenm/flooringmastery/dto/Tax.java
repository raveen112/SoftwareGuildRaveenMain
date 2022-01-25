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
public class Tax {

    //add constructor
    String stateAbbreviation;
    String stateName;
    BigDecimal rawTax;

    public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.rawTax = taxRate;
    }

    public String getStateName() {
        return stateName;
    }

    

    public BigDecimal getRawTax() {
        return rawTax;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    

}



//add constructor

// only getters for TAX
//StateAbbreviation – String
//StateName – String
//TaxRate – BigDecimal
//}

