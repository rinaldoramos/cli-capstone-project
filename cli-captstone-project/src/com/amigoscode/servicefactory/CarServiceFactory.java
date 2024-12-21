package com.amigoscode.servicefactory;

import com.amigoscode.service.ICarService;
import com.amigoscode.service.ICarServiceImpl;

public class CarServiceFactory {

    private static ICarService carService = null;

    private CarServiceFactory(){}

    public static ICarService getCarService() {
        if (carService == null) {
            carService = new ICarServiceImpl();
        }
        return carService;
    }
}
