/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.classroster.service;

import com.raveenm.classroster.dao.ClassRosterAuditDao;
import com.raveenm.classroster.dao.ClassRosterPersistenceException;


/**
 *
 * @author ravee
 */
public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        //do nothing...
    }

}