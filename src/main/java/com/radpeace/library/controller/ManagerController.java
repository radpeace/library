package com.radpeace.library.controller;

import com.radpeace.library.entity.BookEntity;
import com.radpeace.library.entity.GenreEntity;
import com.radpeace.library.exception.BookAlreadyExistException;
import com.radpeace.library.exception.BookNotFoundException;
import com.radpeace.library.model.Book;
import com.radpeace.library.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/books")
    public ResponseEntity getBooks() {
        try {
            return ResponseEntity.ok(managerService.getBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
    @GetMapping("/books/{bookId}")
    public ResponseEntity getBook(@PathVariable Long bookId) {
        try {
            return ResponseEntity.ok(managerService.getBook(bookId));
        } catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
    @PostMapping("/books")
    public ResponseEntity addBook(@RequestBody BookEntity newBook) {
        try {
            managerService.addBook(newBook);
            return ResponseEntity.ok("Книга добавлена");
        } catch (BookAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Long bookId) {
        try {
            managerService.deleteBook(bookId);
            return ResponseEntity.ok("Книга с идентификатором " + bookId + " удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
    @PutMapping("/books/{bookId}")
    public ResponseEntity updateBook(@PathVariable Long bookId, @RequestBody BookEntity updateBook) {
        try {
            managerService.updateBook(bookId, updateBook);
            return ResponseEntity.ok("Книга с идентификатором " + bookId + " обновлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
    @GetMapping("/genres")
    public ResponseEntity getGenres() {
        try {
            return ResponseEntity.ok(managerService.getGenres());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
    @PostMapping("/genres")
    public ResponseEntity addGenre(@RequestBody GenreEntity newGenre) {
        try {
            managerService.addGenre(newGenre);
            return ResponseEntity.ok("Жанр добавлен");
        } catch (BookAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }




//    @GetMapping("/issued-books")
//    public String issuedBooks(Model model) {
//        model.addAttribute("title", "Выданные книги");
//        return "issued-books";
//    }
//    @GetMapping("/debtors")
//    public String debtors(Model model) {
//        model.addAttribute("title", "Должники");
//        return "debtors";
//    }
}