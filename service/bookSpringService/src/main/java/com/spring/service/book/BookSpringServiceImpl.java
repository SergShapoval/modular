package com.spring.service.book;

import com.book.AddBookDTO;
import com.book.Book;
import com.spring.dao.book.BookSpringDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookSpringServiceImpl implements BookSpringService {

    @Autowired
    private BookSpringDAO bookSpringDAO;

    public Book getBook(int bookId) {
        return bookSpringDAO.getBookById(bookId);
    }

    public Book addBook(AddBookDTO addBookDTO) {
        addBookDTO.setName(addBookDTO.getName().toUpperCase());
        return bookSpringDAO.addBook(addBookDTO);
    }

}
