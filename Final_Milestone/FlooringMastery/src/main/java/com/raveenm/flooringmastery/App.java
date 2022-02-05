/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery;

import com.raveenm.flooringmastery.controller.FlooringMasteryController;
import com.raveenm.flooringmastery.dao.FlooringMasteryDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryProductDaoFileImpl;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDao;
import com.raveenm.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl;
import com.raveenm.flooringmastery.service.FlooringMasteryService;
import com.raveenm.flooringmastery.service.FlooringMasteryServiceFileImpl;
import com.raveenm.flooringmastery.ui.FlooringMasteryView;
import com.raveenm.flooringmastery.ui.UserIO;
import com.raveenm.flooringmastery.ui.UserIOConsoleImpl;
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


//        String name = "Acme, Inc.";
//        String state = "CA";
//        String product = "Tile";
//        BigDecimal area = new BigDecimal("249").setScale(2, RoundingMode.UP);
//                
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
//        LocalDate date = LocalDate.parse("01272022", formatter);
//                
//        Order testOrder = new Order(name, state, product, area, date);
//        Order processedOrder = service.getOrderSummary(testOrder);
//        Order addOrder = service.addOrder(processedOrder);
        
        
        
        
        
//        String state1 = "TX";
//        String product1 = "Wood";
//        BigDecimal area1 = new BigDecimal("653").setScale(2, RoundingMode.UP);
//        addOrder.setState(state1);
//        addOrder.setArea(area1);
//        addOrder.setProductType(product1);
//        Order processedOrder2 = service.getOrderSummary(addOrder);
//        service.editOrder(processedOrder2);
        
        
        

    }
}
