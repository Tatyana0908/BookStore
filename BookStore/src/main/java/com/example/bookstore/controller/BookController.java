package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/book")
    public List<BookDto> getAllBooks() {
        return service.getAll();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        Optional<BookDto> bookOptional = service.getById(id);
        if (bookOptional.isPresent()) {
            BookDto book = bookOptional.get();
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            String errorMessage = "Книга не найдена с id " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        try {
            service.deleteBook(id);
            return new ResponseEntity<>("Книга удалена", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка удаления книги: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/book/new")
    public ResponseEntity<String> addBook(@RequestBody BookDto book) {
        try {
            service.addNewBook(book);
            return new ResponseEntity<>("Книга добавлена", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка добавления книги: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/book/edit/{id}")
    public ResponseEntity<String> editBook(@RequestBody BookDto book, @PathVariable Integer id) {
        try {
            service.updateBook(book, id);
            return new ResponseEntity<>("Книга обновлена", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка обновления книги: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
