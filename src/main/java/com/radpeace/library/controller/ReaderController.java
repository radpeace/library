package com.radpeace.library.controller;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.entity.Reader;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.repository.IssuedBookRepository;
import com.radpeace.library.repository.ReaderRepository;
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

    private int readerId = 1; //так как это задание без авторизации, id'ник укажем 1

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
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
    @PostMapping("/reader-books/{bookId}/take")
    public String takeBook(@PathVariable(value = "bookId") int bookId, Model model) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Reader reader = readerRepository.findById(readerId);

        IssuedBook issuedBook = new IssuedBook();
        issuedBook.setBookId(book);
        issuedBook.setReaderId(reader);
        issuedBook.setDateIssue(LocalDate.now());

        issuedBookRepository.save(issuedBook);
        return "redirect:/reader-books";
    }
    @GetMapping("/my-books")
    public String myBooks(Model model) {
//        Reader reader = readerRepository.findById(readerId).orElseThrow();
        List<IssuedBook> issuedBooks = issuedBookRepository.findAllByReaderId(readerId);


        Iterable<Book> myBooks = bookRepository.findMyBooks(readerRepository.findById(readerId));
        model.addAttribute("myBooks", myBooks);
        return "my-books";
    }
    @PostMapping("/my-books/{bookId}/pass")
    public String passBook(@PathVariable(value = "bookId") int bookId, Model model) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Reader reader = readerRepository.findById(readerId);

        IssuedBook issuedBook = issuedBookRepository.findByBookId(book);
        issuedBook.setDateReturn(LocalDate.now());

        issuedBookRepository.save(issuedBook);
        return "redirect:/my-books";
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