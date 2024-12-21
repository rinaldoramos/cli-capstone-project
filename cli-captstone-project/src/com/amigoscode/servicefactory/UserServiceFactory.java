package com.amigoscode.servicefactory;

import com.amigoscode.service.IUserService;
import com.amigoscode.service.IUserServiceImpl;

public class UserServiceFactory {

    private static IUserService userService = null;

    private UserServiceFactory(){}

    public static IUserService getUserService() {
        if (userService == null) {
            userService = new IUserServiceImpl();
        }

        return userService;
    }
}
