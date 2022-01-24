/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.addressbook.dao;

import com.raveenm.addressbook.dto.Contact;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ravee
 */
public class AddressBookDaoImpl implements AddressBookDao{
    
    private Map<String, Contact> contacts = new HashMap<>();
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELMITER = ":";

    @Override
    public Contact addContact(String firstName, Contact contact) {
        Contact prevPerson = contacts.put(firstName, contact);
        return prevPerson;
    }

    @Override
    public Contact removeAddress(String lastName) {
       Contact removeAddress = contacts.remove(lastName);
       return removeAddress;
    }

    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<Contact>(contacts.values());
    }

    @Override
    public Contact getContact(String lastName) {
        return contacts.get(lastName);
    }

    @Override
    public int countContacts() {
        return contacts.size();
    }
    
    // think about marshalling and unmarshalling after. (Phase 1 complete)
    private Contact unmarshallAddress(String addressAsText){
       String[] addressTokens = addressAsText.split(DELMITER);
       String personId = addressTokens[0];
       Contact addressFromFile = new Contact (personId);
       addressFromFile.setFirstName(addressTokens[1]);
       addressFromFile.setLastName(addressTokens[2]);
       addressFromFile.setStreetAddress(addressTokens[2]);
       
       return addressFromFile;
    }
    
}
