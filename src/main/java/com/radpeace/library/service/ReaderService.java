package com.radpeace.library.service;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Reader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReaderService {

    Book findBookById(Long bookId);
    List<Book> findAllBooks();
    void takeBook(Long readerId);
    void passBook(Long readerId);
    List<Book> findMyBooks();
    List<Book> findReservedBooks();

    Reader findReaderById(Long readerId);

}
