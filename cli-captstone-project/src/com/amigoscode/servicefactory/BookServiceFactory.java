package com.amigoscode.servicefactory;

import com.amigoscode.service.IBookService;
import com.amigoscode.service.IBookServiceImpl;

public class BookServiceFactory {

    private static IBookService bookService = null;

    private BookServiceFactory(){}

    public static IBookService getBookService() {
        if (bookService == null) {
            bookService = new IBookServiceImpl();
        }
        return bookService;
    }
}
