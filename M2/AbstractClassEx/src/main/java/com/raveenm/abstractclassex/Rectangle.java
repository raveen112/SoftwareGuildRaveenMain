/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.abstractclassex;

/**
 *
 * @author ravee
 */
public abstract class Rectangle extends Shape {
    
    public float length;
    public float width;
    
    
    
    public Rectangle(float length, float width, String color){
        this.length = length;
        this.width = width;
        this.color = color;
        
    }
    
    @Override
     public float getArea(){
        return this.length*this.width;
    }
    
    @Override
    public float getPerimeter(){
        return 2*(this.length+this.width);
    }
    
    @Override
    public String getColor(){
        return this.color;
    }
}
    

