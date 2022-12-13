package com.radpeace.library.controller;

import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @GetMapping("/books")
    public ResponseEntity getBooks() {
        try {
            return ResponseEntity.ok(readerService.getBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
    @GetMapping("/books/{bookId}")
    public ResponseEntity getBook(@PathVariable Long bookId) {
        try {
            return ResponseEntity.ok(readerService.getBook(bookId));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
//
//    @GetMapping("/")
//    public String tz(Model model) {
//        model.addAttribute("title", "Текстовое задание");
//        return "tz";
//    }
//    @GetMapping("/reader-books")
//    public String readerBooks(Model model) {
//        Iterable<Book> readerBooks = readerService.findAllBooks();
//        model.addAttribute("readerBooks", readerBooks);
//        return "reader-books";
//    }
//
//    @GetMapping("/my-books")
//    public String myBooks(Model model) {
//
//
//
//        Iterable<Book> myBooks = readerService.findMyBooks();
//        model.addAttribute("myBooks", myBooks);
//        return "my-books";
//    }
//
//    @GetMapping("/reserved-books")
//    public String reservedbooks(Model model) {
//        model.addAttribute("title", "Забронированные книги");
//        return "reserved-books";
//    }
//    @GetMapping("/notifications")
//    public String notifications(Model model) {
//        model.addAttribute("title", "Уведомления");
//        return "notifications";
//    }

}