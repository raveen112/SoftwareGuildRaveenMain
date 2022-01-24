/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.classmodeling2;

/**
 *
 * @author ravee
 */
public class House1 {  
     private String houseNumber;                    //read-only as there are no user inputs
     private String streetName;                     //read-only as there are no user inputs 
     private String areaCode;                       //read-only as there are no user inputs

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
     
}
