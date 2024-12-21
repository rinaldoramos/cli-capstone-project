package com.amigoscode.dao;

import com.amigoscode.model.User;

public interface IUserDao {

    String addUser(String name);

    User getUserByID(String userID);

    User[] gettAllUsers();
}
