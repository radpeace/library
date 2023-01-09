package com.radpeace.library.controller;

import com.radpeace.library.dto.mapper.BookMapper;
import com.radpeace.library.dto.mapper.GenreMapper;
import com.radpeace.library.dto.model.BookDto;
import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Genre;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.exception.AlreadyExistException;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/books/{offset}/{pageSize}/{field}")
    public ResponseEntity<List<BookDto>> getBooks(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {

        return ResponseEntity.ok(managerService.getBooks(offset, pageSize, field));

//        try {
//            return ResponseEntity.ok(managerService.getBooks());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }

//        if (managerService.getBooks().isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity(managerService.getBooks(), HttpStatus.OK);
    }
    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) {

        return ResponseEntity.ok(managerService.getBook(bookId));

//        try {
//            return ResponseEntity.ok(managerService.getBook(bookId));
//        } catch (NotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
    }
    @PostMapping("/books")
    public ResponseEntity<BookDto> addBook(@RequestBody Book newBook) {
        managerService.addBook(newBook);
        Long id = newBook.getId();
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(1)
//                .toUri();
//        return ResponseEntity.created(location).build();
        URI location = MvcUriComponentsBuilder.fromController(getClass()).path("/books/{id}").buildAndExpand(newBook.getId()).toUri();
        return ResponseEntity.created(location).body(managerService.getBook(id));

//        try {
//            managerService.addBook(newBook);
//            return ResponseEntity.ok("Книга добавлена");
//        } catch (AlreadyExistException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
    }
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Long bookId) {
        managerService.deleteBook(bookId);
        return ResponseEntity.ok("Книга с идентификатором " + bookId + " удалена");
//        try {
//            managerService.deleteBook(bookId);
//            return ResponseEntity.ok("Книга с идентификатором " + bookId + " удалена");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
    }
    @PutMapping("/books/{bookId}")
    public ResponseEntity updateBook(@PathVariable Long bookId, @RequestBody Book updateBook) {
        managerService.updateBook(bookId, updateBook);
        return ResponseEntity.ok("Книга с идентификатором " + bookId + " обновлена");
//        try {
//            managerService.updateBook(bookId, updateBook);
//            return ResponseEntity.ok("Книга с идентификатором " + bookId + " обновлена");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
    }
    @GetMapping("/genres")
    public ResponseEntity getGenres() {
        return ResponseEntity.ok(managerService.getGenres());

//        try {
//            return ResponseEntity.ok(managerService.getGenres());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
    }

    @PostMapping("/genres")
    public ResponseEntity addGenres(@RequestBody Genre newGenre) {
        return new ResponseEntity(GenreMapper.toGenreDto(managerService.addGenre(newGenre)), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{genreId}")
    public ResponseEntity deleteGenre(@PathVariable Long genreId) {
        managerService.deleteGenre(genreId);
        return ResponseEntity.ok("Жанр с идентификатором " + genreId + " удалён");
    }

    @GetMapping("/issued-books")
    public ResponseEntity getIssuedBooks() {
        return ResponseEntity.ok(managerService.getIssuedBooks());

//        try {
//            return ResponseEntity.ok(managerService.getIssuedBooks());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
    }

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody IssuedBook newIssue) {
        managerService.issueBook(newIssue);
        return ResponseEntity.ok("Книга успешно выдана");

//        try {
//            managerService.issueBook(newIssue);
//            return ResponseEntity.ok("Книга успешно выдана");
//        } catch (AlreadyExistException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
    }

    @PutMapping("/takeaway/")
    public ResponseEntity takeAwayBook(@RequestParam Long bookId) {
        managerService.takeAwayBook(bookId);
        return ResponseEntity.ok("Книга успешно сдана");

//        try {
//            managerService.takeAwayBook(bookId);
//            return ResponseEntity.ok("Книга успешно сдана");
////        } catch (NotFoundException e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ошибка");
//        }
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