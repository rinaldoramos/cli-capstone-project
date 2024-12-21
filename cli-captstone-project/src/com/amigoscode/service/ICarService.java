package com.amigoscode.service;

import com.amigoscode.model.Car;

public interface ICarService {

    Car[] getAllElectricCars();

    Car[] getAllCars();

    Car getCarByRegistration(int register);
}
