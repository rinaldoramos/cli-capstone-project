package com.amigoscode.service;

import com.amigoscode.dao.ICarDao;
import com.amigoscode.daofactory.CarDaoFactory;
import com.amigoscode.model.Car;
import com.amigoscode.servicefactory.CarServiceFactory;

public class ICarServiceImpl implements ICarService {

    private ICarDao carDao = null;

    @Override
    public Car[] getAllElectricCars() {
        if (carDao == null) {
            carDao = CarDaoFactory.getCarDao();
        }
        return carDao.getAllElectricCars();
    }

    @Override
    public Car[] getAllCars() {
        if (carDao == null) {
            carDao = CarDaoFactory.getCarDao();
        }
        return carDao.getAllCars();
    }

    @Override
    public Car getCarByRegistration(int register) {
        if (carDao == null) {
            carDao = CarDaoFactory.getCarDao();
        }
        return carDao.getCarByRegistration(register);
    }
}
