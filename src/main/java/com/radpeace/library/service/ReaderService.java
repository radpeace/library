package com.radpeace.library.service;

import com.radpeace.library.entity.BookEntity;
import com.radpeace.library.entity.Reader;

import java.util.List;

public interface ReaderService {

    BookEntity findBookById(Long bookId);
    List<BookEntity> findAllBooks();
//    void takeBook(Long readerId);
//    void passBook(Long readerId);
    List<BookEntity> findMyBooks();
    List<BookEntity> findReservedBooks();

    Reader findReaderById(Long readerId);

}
