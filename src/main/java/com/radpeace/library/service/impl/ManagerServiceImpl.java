package com.radpeace.library.service.impl;

import com.radpeace.library.dto.mapper.BookMapper;
import com.radpeace.library.dto.mapper.GenreMapper;
import com.radpeace.library.dto.mapper.IssuedBookMapper;
import com.radpeace.library.dto.model.BookDto;
import com.radpeace.library.dto.model.IssuedBookDto;
import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Genre;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.exception.AlreadyExistException;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.dto.model.GenreDto;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.repository.GenreRepository;
import com.radpeace.library.repository.IssuedBookRepository;
import com.radpeace.library.repository.ReaderRepository;
import com.radpeace.library.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBook(Long bookId) throws NotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Книга с таким идентификатором не найдена"));
        return BookMapper.toBookDto(book);
    }

    @Override
    public Book addBook(Book newBook) throws AlreadyExistException {
        if (bookRepository.findByVendor(newBook.getVendor()) != null) {
            throw new AlreadyExistException("Книга с таким артиклем уже существует");
        }
        newBook.getGenres().forEach(genre -> genre.setGenreBookId(newBook));
        newBook.getAuthors().forEach(author -> author.setAuthorBookId(newBook));
        return bookRepository.save(newBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book updateBook(Long bookId, Book updateBook) throws NotFoundException {
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setVendor(updateBook.getVendor());
                    book.setTitle(updateBook.getTitle());
                    book.setDescription(updateBook.getDescription());

                    book.getGenres().clear();
                    updateBook.getGenres().forEach(g -> g.setGenreBookId(book));
                    book.getGenres().addAll(updateBook.getGenres());
                    book.getAuthors().clear();
                    updateBook.getAuthors().forEach(g -> g.setAuthorBookId(book));
                    book.getAuthors().addAll(updateBook.getAuthors());

                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new NotFoundException("Книга с таким идентификатором не найдена"));
    }

    @Override
    public List<GenreDto> getGenres() {
//        List<Genre> genres = genreRepository.findAll();
//        List<GenreDto> genresModel = genreRepository.findAll().stream().map(GenreMapper::toGenreDto)
//                .collect(Collectors.toList());
//        genresModel.stream().distinct();
        return genreRepository.findAll().stream().filter(distinctByKey(Genre::getTitle)).map(GenreMapper::toGenreDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssuedBookDto> getIssuedBooks() {
        return issuedBookRepository.findAll().stream().map(IssuedBookMapper::toIssuedBookDto).collect(Collectors.toList());
    }

    @Override
    public void issueBook(IssuedBook newIssue) throws AlreadyExistException {
        IssuedBook issuedBook = issuedBookRepository.findByBookId(bookRepository.findById(newIssue.getBookId().getId()).get());
        if (issuedBook != null && issuedBook.getDateReturn() == null) {
            throw new AlreadyExistException("Ошибка, книга уже выдана");
        }
        newIssue.setDateIssue(LocalDate.now());
        if (newIssue.getRequiredDateReturn() == null) newIssue.setRequiredDateReturn(LocalDate.now());
        issuedBookRepository.save(newIssue);
    }

    @Override
    public void takeAwayBook(Long bookId) {
        IssuedBook issuedBook = issuedBookRepository.findByBookId(bookRepository.findById(bookId).get());
        issuedBook.setDateReturn(LocalDate.now());
        issuedBookRepository.save(issuedBook);
    }






















    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
