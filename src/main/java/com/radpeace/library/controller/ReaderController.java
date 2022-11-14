package com.radpeace.library.controller;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.entity.Reader;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.repository.IssuedBookRepository;
import com.radpeace.library.repository.ReaderRepository;
import com.radpeace.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @GetMapping("/")
    public String tz(Model model) {
        model.addAttribute("title", "Текстовое задание");
        return "tz";
    }
    @GetMapping("/reader-books")
    public String readerBooks(Model model) {
        Iterable<Book> readerBooks = readerService.findAllBooks();
        model.addAttribute("readerBooks", readerBooks);
        return "reader-books";
    }
    @PostMapping("/reader-books/{bookId}/take")
    public String takeBook(@PathVariable(value = "bookId") Long bookId) {
        readerService.takeBook(bookId);
        return "redirect:/reader-books";
    }
    @GetMapping("/my-books")
    public String myBooks(Model model) {



        Iterable<Book> myBooks = readerService.findMyBooks();
        model.addAttribute("myBooks", myBooks);
        return "my-books";
    }
//    @PostMapping("/my-books/{bookId}/pass")
//    public String passBook(@PathVariable(value = "bookId") int bookId, Model model) {
//        Book book = bookRepository.findById(bookId).orElseThrow();
//        Reader reader = readerRepository.findById(readerId);
//
//        IssuedBook issuedBook = issuedBookRepository.findByBookId(book);
//        issuedBook.setDateReturn(LocalDate.now());
//
//        issuedBookRepository.save(issuedBook);
//        return "redirect:/my-books";
//    }
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