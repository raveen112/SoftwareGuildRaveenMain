/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery;

import com.raveenm.flooringmastery.controller.FlooringMasteryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ravee
 */
public class App {

    public static void main(String[] args) {

//        FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
//        UserIO io = new UserIOConsoleImpl();
//        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoFileImpl();
//        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoFileImpl();
//        FlooringMasteryService service = new FlooringMasteryServiceFileImpl(dao, taxDao, productDao);
//        FlooringMasteryView view = new FlooringMasteryView(io);
//        FlooringMasteryController controller = new FlooringMasteryController(view, service);
//        
//        controller.run();
//        
           ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
           FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
           controller.run();
        
        

    }
}
