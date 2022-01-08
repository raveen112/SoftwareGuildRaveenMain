/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine;

import com.raveenm.vendingmachine.controller.VendingMachineController;
import com.raveenm.vendingmachine.dao.VendingMachineAuditDao;
import com.raveenm.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.raveenm.vendingmachine.dao.VendingMachineDao;
import com.raveenm.vendingmachine.dao.VendingMachineDaoException;
import com.raveenm.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.raveenm.vendingmachine.service.InsufficientFundsException;
import com.raveenm.vendingmachine.service.NoItemInventoryException;
import com.raveenm.vendingmachine.service.VendingMachineServiceLayer;
import com.raveenm.vendingmachine.service.VendingMachineServiceLayerFileImpl;
import com.raveenm.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author ravee
 */
public class App {

    public static void main(String[] args) throws VendingMachineDaoException, InsufficientFundsException, NoItemInventoryException {
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerFileImpl(dao, auditDao);
        VendingMachineView view = new VendingMachineView();
        VendingMachineController controller = new VendingMachineController(service, view);
        
        controller.run();
    }

}
