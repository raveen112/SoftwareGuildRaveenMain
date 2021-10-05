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
public abstract class Circle extends Shape {
    
    public float radius;
    public float pi =(float) 3.14;
    
    public Circle(float radius, String color){
        this.radius = radius;
        this.color = color;
        this.pi = pi;
           
    }
    
    @Override
     public float getArea(){
        return 4*(this.pi*this.radius*this.radius);
    }
    
    @Override
    public float getPerimeter(){
        return 2*(this.pi*this.radius);
    }
    
    @Override
    public String getColor(){
        return this.color;
}
}

