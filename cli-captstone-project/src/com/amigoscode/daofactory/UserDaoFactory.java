package com.amigoscode.daofactory;

import com.amigoscode.dao.IUserDao;
import com.amigoscode.dao.IUserDaoImpl;

public class UserDaoFactory {

    private static IUserDao userDao = null;

    private UserDaoFactory(){}

    public static IUserDao getUserDao() {
        if (userDao == null) {
            userDao = new IUserDaoImpl();
        }

        return userDao;
    }
}
