package com.spring.dao.book;

import com.book.AddBookDTO;
import com.book.Book;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BookSpringDAOImpl implements BookSpringDAO {

    private List<Book> bookList;
    private int nextId;

    @PostConstruct
    public void init() {
        Book book1 = new Book(1, "first book");
        Book book2 = new Book(2, "second book");
        Book book3 = new Book(3, "third book");
        bookList = new ArrayList<>(3);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        nextId = 4;
    }


    @Override
    public Book getBookById(final int id) {
        return bookList.stream().filter(book -> book.getId() == id).findFirst().orElse(new Book());
    }

    @Override
    public Book addBook(AddBookDTO addBookDTO) {
        Book newBook = new Book(nextId, addBookDTO.getName());
        bookList.add(newBook);
        nextId++;
        return newBook;
    }
}


