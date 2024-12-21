package com.amigoscode.dao;

import com.amigoscode.model.Car;

public interface ICarDao {

    Car[] getAllElectricCars();

    Car[] getAllCars();

    Car getCarByRegistration(int register);
}
