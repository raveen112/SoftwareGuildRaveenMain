/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author ravee
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao {

    public static String AUDIT_FILE = "audit.txt";

    public VendingMachineAuditDaoFileImpl() {
        AUDIT_FILE = "audit.txt";
    }

    public VendingMachineAuditDaoFileImpl(String file) {
        AUDIT_FILE = file;
    }

    @Override
    public void writeAuditEntry(String entry) throws VendingMachineDaoException, IOException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));

        } catch (IOException e) {
            throw new VendingMachineDaoException("Could not persist audit information", e);
        }

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        out.println(strDate + " : " + entry);
        out.flush();
    }

}
