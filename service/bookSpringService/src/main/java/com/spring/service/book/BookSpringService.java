package com.spring.service.book;

import com.book.AddBookDTO;
import com.book.Book;

public interface BookSpringService {
    Book getBook(int bookId);

    Book addBook(AddBookDTO addBookDTO);
}
