/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.dvdlibrary;

import com.raveenm.dvdlibrary.controller.DvdLibraryController;
import com.raveenm.dvdlibrary.dao.DvdLibraryDao;
import com.raveenm.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.raveenm.dvdlibrary.ui.DvdLibraryView;
import com.raveenm.dvdlibrary.ui.UserIO;
import com.raveenm.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author ravee
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);
        controller.run();
    }
}
