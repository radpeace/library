package com.radpeace.library.service.impl;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.entity.Reader;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.repository.IssuedBookRepository;
import com.radpeace.library.repository.ReaderRepository;
import com.radpeace.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void takeBook(Long bookId) {
        Book book = findBookById(bookId);
        Reader reader = findReaderById(readerId);

        IssuedBook issuedBook = new IssuedBook(reader, book, LocalDate.now());
        issuedBookRepository.save(issuedBook);
    }

    @Override
    public void passBook(Long bookId) {
        IssuedBook issuedBook = issuedBookRepository.findByBookId(bookId);
        issuedBook.setDateReturn(LocalDate.now());
        issuedBookRepository.save(issuedBook);
    }

    @Override
    public List<Book> findMyBooks() {
//        List<IssuedBook> issuedBooks = issuedBookRepository.findAllByReaderId(readerId);
        List<Book> myBooks = bookRepository.findMyBooks(readerId);
        return myBooks;
    }

    @Override
    public List<Book> findReservedBooks() {
        return null;
    }

    @Override
    public Reader findReaderById(Long readerId) {
        return readerRepository.findById(readerId).orElseThrow();
    }
}
