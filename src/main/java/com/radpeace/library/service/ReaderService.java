package com.radpeace.library.service;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Reader;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.dto.model.BookDto;

import java.util.List;

public interface ReaderService {

    List<BookDto> getBooks();
    BookDto getBook(Long bookId) throws NotFoundException;
    List<BookDto> getMyBooks();
    List<Book> getReservedBooks();

    Reader getReaderById(Long readerId);

}
