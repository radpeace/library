package com.radpeace.library.service.impl;

import com.radpeace.library.dto.mapper.BookMapper;
import com.radpeace.library.dto.model.BookDto;
import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Reader;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.repository.IssuedBookRepository;
import com.radpeace.library.repository.ReaderRepository;
import com.radpeace.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBook(Long bookId) throws NotFoundException {
        return bookRepository.findById(bookId).map(BookMapper::toBookDto).orElseThrow(() -> new NotFoundException("Книга с таким идентификатором не найдена"));
    }

    @Override
    public List<BookDto> getMyBooks() {
//        List<Book> myBooks = bookRepository.findMyBooks(readerId);
        return bookRepository.findMyBooks(readerId).stream().map(BookMapper::toBookDto).collect(Collectors.toList());
    }

//    @Override
//    public void takeBook(Long bookId) {
//        BookDto book = findBookById(bookId);
//        Reader reader = findReaderById(readerId);
//
//        IssuedBook issuedBook = new IssuedBook(reader, book, LocalDate.now());
//        issuedBookRepository.save(issuedBook);
//    }
//
//    @Override
//    public void passBook(Long bookId) {
//        BookDto book = findBookById(bookId);
//        IssuedBook issuedBook = issuedBookRepository.findByBookId(book);
//
//        issuedBook.setDateReturn(LocalDate.now());
//        issuedBookRepository.save(issuedBook);
//    }

    @Override
    public List<Book> getReservedBooks() {
        return null;
    }

    @Override
    public Reader getReaderById(Long readerId) {
        return readerRepository.findById(readerId).orElseThrow();
    }
}
