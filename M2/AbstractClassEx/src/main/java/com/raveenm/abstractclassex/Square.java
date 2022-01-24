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
public class Square extends Shape {
    float side;
    
    public Square(float side, String color){
        this.side = side;
        this.color = color;
    }
    
    @Override
    public float getArea(){
        return this.side*this.side;
    }
    
    @Override
    public float getPerimeter(){
        return 4*(this.side);
    }
    
    @Override
    public String getColor(){
        return this.color;
    }
}

