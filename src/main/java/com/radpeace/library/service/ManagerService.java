package com.radpeace.library.service;

import com.radpeace.library.entity.BookEntity;
import com.radpeace.library.entity.GenreEntity;
import com.radpeace.library.exception.BookAlreadyExistException;
import com.radpeace.library.exception.BookNotFoundException;
import com.radpeace.library.model.Book;
import com.radpeace.library.model.Genre;

import java.util.List;

public interface ManagerService {

//    List<BookEntity> getBooks ();
    List<Book> getBooks ();
    Book getBook (Long bookId) throws BookNotFoundException;
    BookEntity addBook (BookEntity newBook) throws BookAlreadyExistException;
    BookEntity updateBook (Long bookId, BookEntity updateBook) throws BookNotFoundException;
    void deleteBook (Long bookId);
    List<Genre> getGenres ();
    GenreEntity addGenre (GenreEntity newGenre) throws BookAlreadyExistException;
//    void editUser (Long userId);
//    void issueBook (Long readerId, Long bookId);
//    void takeAwayBook (Long readerId, Long bookId);

}
