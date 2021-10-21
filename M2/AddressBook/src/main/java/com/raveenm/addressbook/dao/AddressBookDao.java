/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.addressbook.dao;

import com.raveenm.addressbook.dto.Contact;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface AddressBookDao {
    
    
    Contact addContact (String firstName, Contact contact);
        
    Contact removeAddress (String lastName);
    
    List<Contact> getAllContacts();
    
    Contact getContact(String lastName);
    
    int countContacts();
    
}
