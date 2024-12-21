package com.amigoscode.service;

import com.amigoscode.model.Booking;
import com.amigoscode.model.Car;
import com.amigoscode.model.User;

public interface IBookService {
    boolean bookCar(User user, Car car);

    Booking[] getAllBookings();

    Booking getUserBook(User user);
}
