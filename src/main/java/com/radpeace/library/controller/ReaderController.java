package com.radpeace.library.controller;

import com.radpeace.library.dto.model.BookDto;
import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Booking;
import com.radpeace.library.entity.Comment;
import com.radpeace.library.entity.Queue;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.service.BookingService;
import com.radpeace.library.service.QueueService;
import com.radpeace.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private QueueService queueService;

    @GetMapping("/books/{offset}/{pageSize}/{field}")
    public ResponseEntity getBooks(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        return ResponseEntity.ok(readerService.getBooks(offset, pageSize, field));

//        try {
//            return ResponseEntity.ok(readerService.getBooks());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
    }
    @GetMapping("/books/search={field}")
    public ResponseEntity findBooksByTitle(@PathVariable String field) {
        return ResponseEntity.ok(readerService.findBooksByField(field));
    }
    @GetMapping("/books/{bookId}")
    public ResponseEntity getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(readerService.getBook(bookId));

//        try {
//            return ResponseEntity.ok(readerService.getBook(bookId));
//        } catch (NotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
    }

    @PostMapping("/books/{id}/add-comment")
    public ResponseEntity addComment(@RequestBody Comment newComment, @PathVariable Long id) {
        readerService.addComment(newComment, id);
        return new ResponseEntity("Комментарий добавлен", HttpStatus.CREATED);
    }

    @PostMapping("/books/{id}/add-booking")
    public ResponseEntity addBooking(@RequestBody Booking newBooking, @PathVariable Long id) {
        bookingService.addBooking(newBooking, id);
        return new ResponseEntity("Книга забронирована", HttpStatus.CREATED);
    }

    @PostMapping("/books/{id}/add-queue")
    public ResponseEntity addQueue(@RequestBody Queue newQueue, @PathVariable Long id) {
        queueService.addQueue(newQueue, id);
        return new ResponseEntity("Успех! Вы в очереди", HttpStatus.CREATED);
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