package com.radpeace.library.controller;

import com.radpeace.library.entity.BookEntity;
import com.radpeace.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderController {

//    @Autowired
//    private ReaderService readerService;
//
//    @GetMapping("/")
//    public String tz(Model model) {
//        model.addAttribute("title", "Текстовое задание");
//        return "tz";
//    }
//    @GetMapping("/reader-books")
//    public String readerBooks(Model model) {
//        Iterable<BookEntity> readerBooks = readerService.findAllBooks();
//        model.addAttribute("readerBooks", readerBooks);
//        return "reader-books";
//    }
//
//    @GetMapping("/my-books")
//    public String myBooks(Model model) {
//
//
//
//        Iterable<BookEntity> myBooks = readerService.findMyBooks();
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