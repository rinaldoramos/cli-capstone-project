package com.amigoscode.daofactory;

import com.amigoscode.dao.IBookDao;
import com.amigoscode.dao.IBookDaoImpl;

public class BookDaoFactory {

    private static IBookDao bookDao = null;

    private BookDaoFactory(){}

    public static IBookDao getBookDao() {
        if (bookDao == null) {
            bookDao = new IBookDaoImpl();
        }
        return bookDao;
    }
}
