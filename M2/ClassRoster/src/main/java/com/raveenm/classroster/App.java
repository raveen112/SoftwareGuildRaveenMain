/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.classroster;

import com.raveenm.classroster.controller.ClassRosterController;
import com.raveenm.classroster.dao.ClassRosterDaoFileImpl;
import com.raveenm.classroster.ui.ClassRosterView;
import com.raveenm.classroster.ui.UserIO;
import com.raveenm.classroster.ui.UserIOConsoleImpl;
import com.raveenm.classroster.dao.ClassRosterDao;

/**
 *
 * @author ravee
 */
public class App {
    public static void main(String[] args) {
       UserIO myIO = new UserIOConsoleImpl();
       ClassRosterView myView = new ClassRosterView(myIO);
       ClassRosterDao myDao = new ClassRosterDaoFileImpl();
       ClassRosterController controller = new ClassRosterController(myDao, myView);
       controller.run();
       
    }
}
