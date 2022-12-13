package com.radpeace.library.service;

import com.radpeace.library.dto.model.BookDto;
import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.exception.AlreadyExistException;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.dto.model.GenreDto;
import com.radpeace.library.dto.model.IssuedBookDto;

import java.util.List;

public interface ManagerService {

//    List<Book> getBooks ();
    List<BookDto> getBooks ();
    BookDto getBook (Long bookId) throws NotFoundException;
    Book addBook (Book newBook) throws AlreadyExistException;
    Book updateBook (Long bookId, Book updateBook) throws NotFoundException;
    void deleteBook (Long bookId);
    List<GenreDto> getGenres ();
//    Genre addGenre (Genre newGenre) throws AlreadyExistException;
//    void editUser (Long userId);
    List<IssuedBookDto> getIssuedBooks ();
    void issueBook (IssuedBook newIssue) throws AlreadyExistException;
    void takeAwayBook (Long bookId);


}
