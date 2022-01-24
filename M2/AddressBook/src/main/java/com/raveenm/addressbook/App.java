/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.addressbook;

import com.raveenm.addressbook.controller.AddressBookController;
import com.raveenm.addressbook.dao.AddressBookDao;
import com.raveenm.addressbook.dao.AddressBookDaoImpl;
import com.raveenm.addressbook.ui.AddressBookView;
import com.raveenm.addressbook.ui.UserIO;
import com.raveenm.addressbook.ui.UserIOConsoleImpl;

/**
 *
 * @author ravee
 */
public class App {
    
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        AddressBookView myView = new AddressBookView(myIo);
        AddressBookDao myDao = new AddressBookDaoImpl();
        AddressBookController controller = new AddressBookController (myDao, myView);
        
        controller.run();
    }
}
