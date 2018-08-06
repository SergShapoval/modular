package com.spring.dao.book;

import com.book.AddBookDTO;
import com.book.Book;

public interface BookSpringDAO {
    Book getBookById(int id);

    Book addBook(AddBookDTO book);
}
