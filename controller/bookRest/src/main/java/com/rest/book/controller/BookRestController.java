package com.rest.book.controller;

import com.book.AddBookDTO;
import com.book.Book;
import com.spring.service.book.BookSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("")
@RestController
public class BookRestController {

    @Autowired
    private BookSpringService bookSpringService;

    @GetMapping(value = "/rest/book/{id}", produces = "application/json")
    public ResponseEntity<Book> getGetBook(@PathVariable Integer id) {
        return new ResponseEntity<>(bookSpringService.getBook(id), HttpStatus.OK);
    }

    @PostMapping(value = "/rest/book", produces = "application/json")
    public ResponseEntity<Book> postAddBook(@RequestBody AddBookDTO addBookDTO) {
        return new ResponseEntity<>(bookSpringService.addBook(addBookDTO), HttpStatus.OK);
    }

}
