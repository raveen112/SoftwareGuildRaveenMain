/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.classroster;

import com.raveenm.classroster.controller.ClassRosterController;
import com.raveenm.classroster.dao.ClassRosterAuditDao;
import com.raveenm.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.raveenm.classroster.dao.ClassRosterDao;
import com.raveenm.classroster.dao.ClassRosterDaoFileImpl;
import com.raveenm.classroster.service.ClassRosterServiceLayer;
import com.raveenm.classroster.service.ClassRosterServiceLayerImpl;
import com.raveenm.classroster.ui.ClassRosterView;
import com.raveenm.classroster.ui.UserIO;
import com.raveenm.classroster.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ravee
 */
public class App {

    public static void main(String[] args) {
//       UserIO myIO = new UserIOConsoleImpl();
//       ClassRosterView myView = new ClassRosterView(myIO);
//       ClassRosterDao myDao = new ClassRosterDaoFileImpl(); 
//       ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//       ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
//       ClassRosterController controller = new ClassRosterController(myService, myView);
//       controller.run();

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller
                = ctx.getBean("controller", ClassRosterController.class);
        controller.run();
        
    }

}
