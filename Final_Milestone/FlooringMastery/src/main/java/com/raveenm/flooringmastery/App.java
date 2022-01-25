/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery;

import com.raveenm.flooringmastery.dao.FlooringMasteryDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoException;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDaoFileImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl;
import com.raveenm.flooringmastery.service.FlooringMasteryService;
import com.raveenm.flooringmastery.service.FlooringMasteryServiceFileImpl;
import java.math.BigDecimal;

/**
 *
 * @author ravee
 */
public class App {

    public static void main(String[] args) throws FlooringMasteryDaoException {

        FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoFileImpl();
        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoFileImpl();
        FlooringMasteryService service = new FlooringMasteryServiceFileImpl(dao, taxDao, productDao);
        
        
       
    }
}
