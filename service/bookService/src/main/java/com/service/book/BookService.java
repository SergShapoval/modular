package com.service.book;

import com.book.dao.BookDao;

public class BookService {

    private BookDao bookDao;

    public BookService() {
        bookDao = new BookDao();
    }

    public String getBook() {
        return bookDao.getBook();
    }
}
