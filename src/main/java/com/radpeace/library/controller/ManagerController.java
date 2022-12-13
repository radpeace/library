package com.radpeace.library.controller;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.exception.AlreadyExistException;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
    @PostMapping("/books")
    public ResponseEntity addBook(@RequestBody Book newBook) {
        try {
            managerService.addBook(newBook);
            return ResponseEntity.ok("Книга добавлена");
        } catch (AlreadyExistException e) {
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
    public ResponseEntity updateBook(@PathVariable Long bookId, @RequestBody Book updateBook) {
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

    @GetMapping("/issued-books")
    public ResponseEntity getIssuedBooks() {
        try {
            return ResponseEntity.ok(managerService.getIssuedBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody IssuedBook newIssue) {
        try {
            managerService.issueBook(newIssue);
            return ResponseEntity.ok("Книга успешно выдана");
        } catch (AlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PutMapping("/takeaway/")
    public ResponseEntity takeAwayBook(@RequestParam Long bookId) {
        try {
            managerService.takeAwayBook(bookId);
            return ResponseEntity.ok("Книга успешно сдана");
//        } catch (NotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

//    @GetMapping("/takeaway/")
//    public ResponseEntity takeAwayBook(@RequestParam Long bookId) {
//        try {
//
//            return ResponseEntity.ok(managerService.takeAwayBook(bookId));
////        } catch (NotFoundException e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
//    }











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