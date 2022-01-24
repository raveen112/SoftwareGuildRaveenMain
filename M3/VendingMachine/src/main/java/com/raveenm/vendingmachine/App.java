/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine;

import com.raveenm.vendingmachine.controller.VendingMachineController;
import com.raveenm.vendingmachine.dao.VendingMachineDaoException;
import com.raveenm.vendingmachine.service.InsufficientFundsException;
import com.raveenm.vendingmachine.service.NoItemInventoryException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ravee
 */
public class App {

    public static void main(String[] args) throws VendingMachineDaoException, InsufficientFundsException, NoItemInventoryException {
//        VendingMachineDao dao = new VendingMachineDaoFileImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineServiceLayer service = new VendingMachineServiceLayerFileImpl(dao, auditDao);
//        VendingMachineView view = new VendingMachineView();
//        VendingMachineController controller = new VendingMachineController(service, view);
        

            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            VendingMachineController controller = ctx.getBean("vendingMachineController", VendingMachineController.class);
            controller.run();
    }

}
