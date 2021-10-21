/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.addressbook.controller;

import com.raveenm.addressbook.dao.AddressBookDao;
import com.raveenm.addressbook.dao.AddressBookDaoImpl;
import com.raveenm.addressbook.dto.Contact;
import com.raveenm.addressbook.ui.AddressBookView;
import com.raveenm.addressbook.ui.UserIO;
import com.raveenm.addressbook.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author ravee
 */
public class AddressBookController {
    
    private AddressBookView view ;
    private UserIO io = new UserIOConsoleImpl();
    private AddressBookDao dao ;

    public AddressBookController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    public void run(){
    
    boolean keepGoing = true;
    int menuSelection=0;
    
    while(keepGoing){
    
    menuSelection = getMenuSelection();
    
    switch(menuSelection){
                case 1:
                    createAddress();
                    break;
            
                case 2:
                    removeAddress();
                    break;
                case 3:
                    viewAddress();
                    break;
                case 4:
                    countContacts();
                    break;
                
                case 5:
                    listAddress();
                    break;
                
                case 6:
                    keepGoing = false;
                    break;
                    
                default:
                    unknownCommand();
    }
    
   }
    exitBanner();
    
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
   }
    
    private void createAddress(){
        view.displayCreateAddressBanner();
        Contact newAddress = view.getNewAddressInfo();
        dao.addContact(newAddress.getFirstName(), newAddress);
        view.displayCreatedSuccessBanner();
    }
    
    private void listAddress(){
        view.displayDisplayAllBanner();
        List<Contact> contactList = dao.getAllContacts();
        view.displayAddressList(contactList);
    }
    
    private void viewAddress(){
        view.displayDisplayAddressBanner();
        String personId = view.getLastNameChoice();
        Contact person = dao.getContact(personId);
        view.displayAddress(person);
    }
    
    private void removeAddress(){
        view.displayRemoveAddressBanner();
        String personId = view.getLastNameChoice();
        Contact removedAddress = dao.removeAddress(personId);
        view.displayRemoveResult(removedAddress);
    }
    
    private void countContacts(){
        view.displayCounterBanner();
        int personID = dao.countContacts();
        view.displayCounter(personID);
        
    }
    
    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    private void exitBanner(){
        view.displayExitBanner();
    }
    
    public AddressBookController (AddressBookDao dao, AddressBookView view){
        this.dao =dao;
        this.view = view;
        
    }
    
    
  
  
}
