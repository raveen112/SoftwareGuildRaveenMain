package com.raveenm.carlot.service;

import com.raveenm.carlot.dao.CarLotPersistenceException;
import com.raveenm.carlot.dao.CarLotDao;
import com.raveenm.carlot.dto.Car;
import com.raveenm.carlot.dto.CarKey;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ravee
 */
public class CarLotServiceLayerImpl implements CarLotService {

    CarLotDao dao;

    public CarLotServiceLayerImpl(CarLotDao dao) {
        this.dao = dao;
    }

    @Override
    public Car getACar(String VIN) {
        return dao.getCar("123");

    }

    @Override
    public List<Car> getAllCars() {
        return dao.getCars();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return dao.getCars(color);
    }

    @Override
    public List<Car> getCarsInBudget(BigDecimal maxPrice) {
        return dao.getCars(maxPrice);
    }

    @Override
    public List<Car> getCarByMakeAndModel(String make, String model) {
        return dao.getCars(make, model);
    }

    @Override
    public BigDecimal discountCar(String VIN, BigDecimal percentDiscount) throws CarLotNoSuchCarException {
        Car car = dao.getCar(percentDiscount);
        BigDecimal price = car.getPrice();
        price = price.subtract(price.multiply(new BigDecimal(0.15)));
        car.setPrice(price);
        return price;
    }

    @Override
    public CarKey sellCar(String VIN, BigDecimal cashPaid) throws CarLotNoSuchCarException, CarLotOverpaidPriceException, CarLotUnderpaidPriceException {
        Car car = new Car();
        car = dao.getCar(VIN);
        BigDecimal price = car.getPrice();
        
        if(price.compareTo(cashPaid)==0){
            dao.removeCar(VIN);
            return car.getKey();
            
        }else if(price.compareTo(cashPaid)>0){
            
            throw new CarLotOverpaidPriceException("The car doesnt cost that much!");
        }
        else if(price.compareTo(cashPaid)<0)
        {
            throw new CarLotUnderpaidPriceException("You need more money sirji!");
        }
        else{
            
            throw new CarLotNoSuchCarException("No such car in the lot!");
        }
        
    }
}
        
      