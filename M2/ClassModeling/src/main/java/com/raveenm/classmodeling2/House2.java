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
public class House2 {
    private String roofingMaterial;                     //write as the user will enter details to model the house
    private String insulationMaterial;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private Dimensions dimensions;

    public void squareFootage(int a, int b){
        
    }
    
    
    public String getRoofingMaterial() {
        return roofingMaterial;
    }

    public void setRoofingMaterial(String roofingMaterial) {
        this.roofingMaterial = roofingMaterial;
    }

    public String getInsulationMaterial() {
        return insulationMaterial;
    }

    public void setInsulationMaterial(String insulationMaterial) {
        this.insulationMaterial = insulationMaterial;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }
}
