/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.supersightings.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ravee
 */
public class Hero {
    
    private int id;
    private String name;
    private String description;
    private Superpower superPower;
    private List<Organization> organization;
    private byte[] superImage;

    public byte[] getSuperImage() {
        return superImage;
    }

    public void setSuperImage(byte[] superImage) {
        this.superImage = superImage;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.superPower);
        hash = 37 * hash + Objects.hashCode(this.organization);
        hash = 37 * hash + Arrays.hashCode(this.superImage);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superPower, other.superPower)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        if (!Arrays.equals(this.superImage, other.superImage)) {
            return false;
        }
        return true;
    }



    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Superpower getSuperPower() {
        return superPower;
    }

    public void setSuperPower(Superpower superPower) {
        this.superPower = superPower;
    }

    public List<Organization> getOrganizations() {
        return organization;
    }

    public void setOrganization(List<Organization> organizations) {
        this.organization = organizations;
    }
    
}
