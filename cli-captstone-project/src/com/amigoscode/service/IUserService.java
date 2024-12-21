package com.amigoscode.service;

import com.amigoscode.model.User;

public interface IUserService {

    String addUser(String name);

    User getUserByID(String userID);

    User[] gettAllUsers();
}
