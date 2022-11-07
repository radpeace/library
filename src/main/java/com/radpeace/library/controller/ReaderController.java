package com.radpeace.library.controller;

import com.radpeace.library.entity.Book;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.repository.IssuedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReaderController {

    private int readerId = 1;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    @GetMapping("/")
    public String tz(Model model) {
        model.addAttribute("title", "Текстовое задание");
        return "tz";
    }
    @GetMapping("/reader-books")
    public String readerBooks(Model model) {
        Iterable<Book> readerBooks = bookRepository.findAll();
        model.addAttribute("readerBooks", readerBooks);
        return "reader-books";
    }
    @GetMapping("/my-books")
    public String myBooks(Model model) {
        Iterable<Book> myBooks = issuedBookRepository.findAllByReaderId(readerId);
        model.addAttribute("myBooks", myBooks);
        return "my-books";
    }
    @GetMapping("/reserved-books")
    public String reservedbooks(Model model) {
        model.addAttribute("title", "Забронированные книги");
        return "reserved-books";
    }
    @GetMapping("/notifications")
    public String notifications(Model model) {
        model.addAttribute("title", "Уведомления");
        return "notifications";
    }

}