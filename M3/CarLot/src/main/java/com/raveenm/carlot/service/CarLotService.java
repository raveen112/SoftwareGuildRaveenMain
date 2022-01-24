/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.carlot.service;

import com.raveenm.carlot.dao.CarLotPersistenceException;
import com.raveenm.carlot.dto.Car;
import com.raveenm.carlot.dto.CarKey;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface CarLotService {

    public Car getACar(String VIN) throws CarLotNoSuchCarException;

    public List<Car> getAllCars() throws CarLotPersistenceException;

    public List<Car> getCarsByColor(String color)throws CarLotNoSuchCarException;

    public List<Car> getCarsInBudget(BigDecimal maxPrice)throws CarLotOverpaidPriceException,
            CarLotUnderpaidPriceException;;

    public List<Car> getCarByMakeAndModel(String make, String model) throws CarLotNoSuchCarException;

    public BigDecimal discountCar(String VIN, BigDecimal percentDiscount)
            throws CarLotNoSuchCarException;

    public CarKey sellCar(String VIN, BigDecimal cashPaid)
            throws CarLotNoSuchCarException,
            CarLotOverpaidPriceException,
            CarLotUnderpaidPriceException;
}

