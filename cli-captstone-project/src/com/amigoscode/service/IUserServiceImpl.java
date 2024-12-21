package com.amigoscode.service;

import com.amigoscode.dao.IUserDao;
import com.amigoscode.daofactory.UserDaoFactory;
import com.amigoscode.model.User;
import com.amigoscode.servicefactory.UserServiceFactory;

public class IUserServiceImpl implements IUserService {
    private static IUserDao userDao = null;

    @Override
    public String addUser(String name) {
        if (userDao == null) {
            userDao = UserDaoFactory.getUserDao();
        }
        return userDao.addUser(name);
    }

    @Override
    public User getUserByID(String userID) {
        if (userDao == null) {
            userDao = UserDaoFactory.getUserDao();
        }
        return userDao.getUserByID(userID);
    }

    @Override
    public User[] gettAllUsers() {
        if (userDao == null) {
            userDao = UserDaoFactory.getUserDao();
        }
        return userDao.gettAllUsers();
    }
}
