package com.radpeace.library.service;

import com.radpeace.library.dto.model.BookDto;
import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Genre;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.exception.AlreadyExistException;
import com.radpeace.library.exception.NoContentException;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.dto.model.GenreDto;
import com.radpeace.library.dto.model.IssuedBookDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ManagerService {

//    List<Book> getBooks ();
    List<BookDto> getBooks (int offset, int pageSize, String field);
    BookDto getBook (Long bookId);
    Book addBook (Book newBook);
    Book updateBook (Long bookId, Book updateBook);
    void deleteBook (Long bookId);
    List<GenreDto> getGenres ();
//    Genre addGenre (Genre newGenre) throws AlreadyExistException;
//    void editUser (Long userId);
    Genre addGenre (Genre newGenre);
    void deleteGenre (Long genreId);
    List<IssuedBookDto> getIssuedBooks ();
    void issueBook (IssuedBook newIssue);
    void takeAwayBook (Long bookId);


}
