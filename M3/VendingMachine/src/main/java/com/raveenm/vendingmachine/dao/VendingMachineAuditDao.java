/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.dao;

import java.io.IOException;

/**
 *
 * @author ravee
 */
public interface VendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws VendingMachineDaoException, IOException;
}
