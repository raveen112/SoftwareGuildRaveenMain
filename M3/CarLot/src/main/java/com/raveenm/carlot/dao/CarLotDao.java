/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.carlot.dao;

import com.raveenm.carlot.dto.Car;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface CarLotDao {
    public Car addCar(String VIN, Car car);    

    public Car getCar(String VIN);    
    public List<Car> getCars();

    public void editCar(String VIN, Car car);    

    public Car removeCar(String VIN);  
    
    public List<Car> getCars(String color);
    
    public List<Car> getCars(BigDecimal maxPrice);
    
    public List<Car> getCars (String make, String model);
   
    public Car getCar (BigDecimal price);
    
    public Car getCashPaid (BigDecimal cashPaid);
    
}
