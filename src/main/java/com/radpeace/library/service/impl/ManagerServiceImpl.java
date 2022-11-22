package com.radpeace.library.service.impl;

import com.radpeace.library.entity.BookEntity;
import com.radpeace.library.entity.GenreEntity;
import com.radpeace.library.exception.BookAlreadyExistException;
import com.radpeace.library.exception.BookNotFoundException;
import com.radpeace.library.model.Book;
import com.radpeace.library.model.Genre;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.repository.GenreRepository;
import com.radpeace.library.repository.IssuedBookRepository;
import com.radpeace.library.repository.ReaderRepository;
import com.radpeace.library.service.ManagerService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll().stream().map(Book::toModel)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<BookEntity> getBooks() {
//        return bookRepository.findAll();
//    }

    @Override
    public Book getBook(Long bookId) throws BookNotFoundException {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Книга с таким идентификатором не найдена"));
        return Book.toModel(book);
    }

    @Override
    public BookEntity addBook(BookEntity newBook) throws BookAlreadyExistException {
        if (bookRepository.findByVendor(newBook.getVendor()) != null) {
            throw new BookAlreadyExistException("Книга с таким артиклем уже существует");
        }

        newBook.getGenres().forEach(genre -> genre.setGenreBookId(newBook));
        return bookRepository.save(newBook);
    }

    @Override
    public BookEntity test (BookEntity newBook) throws BookAlreadyExistException {
//        newBook.getGenres().forEach(genre -> genre.setGenreBookId(newBook));
        return newBook;
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookEntity updateBook(Long bookId, BookEntity updateBook) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setVendor(updateBook.getVendor());
                    book.setTitle(updateBook.getTitle());
                    book.setDescription(updateBook.getDescription());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new BookNotFoundException("Книга с таким идентификатором не найдена")); //исправить
    }

    @Override
    public List<Genre> getGenres() {
//        List<GenreEntity> genres = genreRepository.findAll();
//        List<Genre> genresModel = genres.stream().map(Genre::toModel)
//                .collect(Collectors.toList());
        return genreRepository.findAll().stream().map(Genre::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public GenreEntity addGenre(GenreEntity newGenre) throws BookAlreadyExistException {
        if (genreRepository.findByGenreBookId(newGenre.getGenreBookId().getId()) != null) {
            throw new BookAlreadyExistException("Такой жанр уже добавлен");                     //исправить
        }
        return genreRepository.save(newGenre);
    }
}
