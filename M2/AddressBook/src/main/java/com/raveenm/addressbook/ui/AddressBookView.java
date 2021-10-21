/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.addressbook.ui;

import com.raveenm.addressbook.dto.Contact;
import java.util.List;

/**
 *
 * @author ravee
 */
public class AddressBookView {
    
    private UserIO io = new UserIOConsoleImpl();
    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add an Address ");
        io.print("2. Remove an Address");
        io.print("3. Find an Address");
        io.print("4. Total Number of Adresses");
        io.print("5. List all Adresses");
        io.print("6. Exit");
        
        return io.readInt("Please select from the above choices. ", 1, 6);
    }
    
    public Contact getNewAddressInfo() {
        String firstName = io.readString("Please enter first name: ");
        String lastName = io.readString("Please enter last name: ");
        String streetAddress = io.readString("Please enter address: ");
        Contact currentAddress = new Contact(firstName);
        currentAddress.setFirstName(firstName);
        currentAddress.setLastName(lastName);
        currentAddress.setStreetAddress(streetAddress);
        return currentAddress;
    }
    //create student block
    public void displayCreateAddressBanner() {
        io.print("===ADD ADDRESS===");
    }
    
    public void displayCreatedSuccessBanner() {
        io.readString("Address Successfully Added. Hit enter to continue.");
    }
    
    //list all address block
    public void displayAddressList(List<Contact> addressList) {
        for (Contact currentAddress : addressList) {
            String addressInfo = String.format("%s %s \n%s\n",
                    currentAddress.getFirstName(),
                    currentAddress.getLastName(),
                    currentAddress.getStreetAddress());
            io.print(addressInfo);
            
        }
        io.readString("Please hit enter to continue");        
    }
    
    public void displayDisplayAllBanner() {
        io.print("==Display All Students===");
    }
    
    // single student display
    public void displayDisplayAddressBanner(){
        io.print("=== Display Single Address ===");
    }
    
    
    public String getLastNameChoice(){
        return io.readString("Please enter last name: ");
    }
    
    public void displayAddress(Contact contact){
        if (contact  != null){
            io.print(contact.getFirstName());
            io.print(contact.getLastName());
            io.print(contact.getStreetAddress());
            io.print("");
            }
        else{
            io.print("No such address.");
            
        }
        io.readString("Please hit enter to continue.");
                    
    }
    
    //remove address block
    public void displayRemoveAddressBanner(){
        io.print("=== Remove Address ===");
    }
    
    public void displayRemoveResult(Contact addressRecord){
        if(addressRecord != null){
            io.print("Address successfully removed. ");
        }else{
            io.print("Address not found.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayCounterBanner(){
    io.print("=== Total Addresses ===");
}
    
    public void displayCounter(int personId){
        io.print( "There are " + personId + " addresses in the book." );
        
        io.readString("Please hit enter to go to Main Menu");
        
    }
    
   public void displayUnknownCommandBanner(){
       io.print("Unknown Command!");
   }
   
   public void displayExitBanner(){
       io.print("Good Bye!");
   }
   
   public AddressBookView(UserIO io){
       this.io = io;
   }
   
   
}
    
    
    
    

