package com.radpeace.library.service.impl;

import com.radpeace.library.entity.BookEntity;
import com.radpeace.library.entity.Reader;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.repository.IssuedBookRepository;
import com.radpeace.library.repository.ReaderRepository;
import com.radpeace.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    private Long readerId = 1L;

//    private final Reader reader = findReaderById(readerId);

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    @Override
    public BookEntity findBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }

    @Override
    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

//    @Override
//    public void takeBook(Long bookId) {
//        Book book = findBookById(bookId);
//        Reader reader = findReaderById(readerId);
//
//        IssuedBook issuedBook = new IssuedBook(reader, book, LocalDate.now());
//        issuedBookRepository.save(issuedBook);
//    }
//
//    @Override
//    public void passBook(Long bookId) {
//        Book book = findBookById(bookId);
//        IssuedBook issuedBook = issuedBookRepository.findByBookId(book);
//
//        issuedBook.setDateReturn(LocalDate.now());
//        issuedBookRepository.save(issuedBook);
//    }

    @Override
    public List<BookEntity> findMyBooks() {
        List<BookEntity> myBooks = bookRepository.findMyBooks(readerId);
        return myBooks;
    }

    @Override
    public List<BookEntity> findReservedBooks() {
        return null;
    }

    @Override
    public Reader findReaderById(Long readerId) {
        return readerRepository.findById(readerId).orElseThrow();
    }
}
