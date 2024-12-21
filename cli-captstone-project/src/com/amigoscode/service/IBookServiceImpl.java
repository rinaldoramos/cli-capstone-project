package com.amigoscode.service;

import com.amigoscode.dao.IBookDao;
import com.amigoscode.daofactory.BookDaoFactory;
import com.amigoscode.model.Booking;
import com.amigoscode.model.Car;
import com.amigoscode.model.User;

public class IBookServiceImpl implements IBookService {
    private IBookDao bookDao = null;

    @Override
    public boolean bookCar(User user, Car car) {
        if (bookDao == null) {
            bookDao = BookDaoFactory.getBookDao();
        }
        return bookDao.bookCar(user, car);
    }

    @Override
    public Booking[] getAllBookings() {
        if (bookDao == null) {
            bookDao = BookDaoFactory.getBookDao();
        }
        return bookDao.getAllBookings();
    }

    @Override
    public Booking getUserBook(User user) {
        if (bookDao == null) {
            bookDao = BookDaoFactory.getBookDao();
        }

        return bookDao.getUserBook(user);
    }
}
