package com.amigoscode.daofactory;

import com.amigoscode.dao.ICarDao;
import com.amigoscode.dao.ICarDaoImpl;

public class CarDaoFactory {

    private static ICarDao carDao = null;

    private CarDaoFactory(){}

    public static ICarDao getCarDao() {
        if (carDao == null) {
            carDao = new ICarDaoImpl();
        }
        return carDao;
    }
}
