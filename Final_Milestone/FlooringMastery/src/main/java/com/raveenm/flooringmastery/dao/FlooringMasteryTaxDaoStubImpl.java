/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ravee
 */
public class FlooringMasteryTaxDaoStubImpl implements FlooringMasteryTaxDao {

    Tax taxTexas = new Tax("TX", "Texas", new BigDecimal("4.45").setScale(2, RoundingMode.HALF_UP));
    Tax taxWashington = new Tax("WA", "Washington", new BigDecimal("9.25").setScale(2, RoundingMode.HALF_UP));
    Tax taxKentucky = new Tax("KY", "Kentucky", new BigDecimal("6.00").setScale(2, RoundingMode.HALF_UP));
    Tax taxCalifornia = new Tax("CA", "California", new BigDecimal("25.00").setScale(2, RoundingMode.HALF_UP));

    @Override
    public Tax getStateTax(String stateAbbreviation) throws OrderPersistenceException {
        if (stateAbbreviation == taxTexas.getStateAbbreviation()) {
            return taxTexas;
            
        } else if ((stateAbbreviation == taxWashington.getStateAbbreviation())) {
            return taxWashington;

        } else if ((stateAbbreviation == taxKentucky.getStateAbbreviation())) {
            return taxKentucky;
            
        } else if ((stateAbbreviation == taxCalifornia.getStateAbbreviation())) {
            return taxCalifornia;
        }
        return null;
    }

    @Override
    public List<Tax> getAllStateTaxes() throws OrderPersistenceException {
        List<Tax> allStateTaxes = new ArrayList<>();

        allStateTaxes.add(taxTexas);
        allStateTaxes.add(taxWashington);
        allStateTaxes.add(taxKentucky);
        allStateTaxes.add(taxCalifornia);

        return allStateTaxes;
    }

}
