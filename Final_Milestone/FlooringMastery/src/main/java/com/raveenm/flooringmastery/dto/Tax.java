/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author ravee
 */
public class Tax {

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.stateAbbreviation);
        hash = 97 * hash + Objects.hashCode(this.stateName);
        hash = 97 * hash + Objects.hashCode(this.rawTax);
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
        final Tax other = (Tax) obj;
        if (!Objects.equals(this.stateAbbreviation, other.stateAbbreviation)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        if (!Objects.equals(this.rawTax, other.rawTax)) {
            return false;
        }
        return true;
    }

    //add constructor
    String stateAbbreviation;
    String stateName;
    BigDecimal rawTax;

    public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.rawTax = taxRate.setScale(2, RoundingMode.HALF_UP);
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

