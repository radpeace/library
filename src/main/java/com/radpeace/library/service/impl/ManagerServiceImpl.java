package com.radpeace.library.service.impl;

import com.radpeace.library.dto.mapper.BookMapper;
import com.radpeace.library.dto.mapper.GenreMapper;
import com.radpeace.library.dto.mapper.IssuedBookMapper;
import com.radpeace.library.dto.model.BookDto;
import com.radpeace.library.dto.model.IssuedBookDto;
import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Booking;
import com.radpeace.library.entity.Genre;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.exception.AlreadyExistException;
import com.radpeace.library.exception.AlreadyIssuedException;
import com.radpeace.library.exception.NoContentException;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.dto.model.GenreDto;
import com.radpeace.library.repository.*;
import com.radpeace.library.service.ManagerService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<BookDto> getBooks(int offset, int pageSize, String field) {
//        Page<Book> pageOfBooks = bookRepository.findAll(PageRequest.of(offset-1, pageSize).withSort(Sort.by(field)));
//        List<Book> listOfBooks = pageOfBooks.getContent();
//        if (books.isEmpty()) throw new NoContentException("Книги не найдены");
//        new PageImpl<>(books, PageRequest.of(offset, pageSize, Sort.by(field).ascending()), books.size());

        return
            bookRepository.findAll(PageRequest.of(offset-1, pageSize).withSort(Sort.by(field)))
            .getContent()
            .stream().map(BookMapper::toBookDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBook(Long bookId){
        return bookRepository.findById(bookId)
                .map(BookMapper::toBookDto)
                .orElseThrow(() -> new NotFoundException("Книга с таким идентификатором не найдена"));
    }

    @Override
    public Book addBook(Book newBook){
//        if (bookRepository.findByIsbn(newBook.getIsbn()) != null) {
//            throw new AlreadyExistException("Книга с таким артиклем уже существует");
//        }
        return bookRepository.save(newBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book updateBook(Long bookId, Book updateBook) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setIsbn(updateBook.getIsbn());
                    book.setTitle(updateBook.getTitle());
                    book.setDescription(updateBook.getDescription());

                    book.getGenres().clear();
                    updateBook.getGenres().forEach(genre -> book.addGenre(genre));
                    book.getAuthors().clear();
                    updateBook.getAuthors().forEach(author -> book.addAuthor(author));

                    return bookRepository.save(book);
                }).get();
    }

    @Override
    public List<GenreDto> getGenres() {
        return genreRepository.findAll().stream().map(GenreMapper::toGenreDto)
                .collect(Collectors.toList());
    }

    @Override
    public Genre addGenre(Genre newGenre) {
        if (genreRepository.findByTitle(newGenre.getTitle()) != null) {
            throw new AlreadyExistException("Такой жанр уже существует");
        }
        return genreRepository.save(newGenre);
    }

    @Override
    public void deleteGenre(Long genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    public List<IssuedBookDto> getIssuedBooks() {
        return issuedBookRepository.findAll().stream().map(IssuedBookMapper::toIssuedBookDto).collect(Collectors.toList());
    }

    @Override
    public void issueBook(IssuedBook newIssue) {
        IssuedBook issuedBook = issuedBookRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(newIssue.getBookId().getId()).get());
        Booking booking = bookingRepository.findTopByBookIdOrderByIdDesc(newIssue.getBookId());
        if (issuedBook != null && issuedBook.getDateReturn() == null) {
            throw new AlreadyIssuedException("Ошибка, книга уже выдана");
        }
        else if (booking != null && LocalDateTime.now().isBefore(booking.getDateFinish())
                && newIssue.getReaderId().getId() != booking.getReaderId().getId()) {
            throw new AlreadyIssuedException("Ошибка, книга уже забронирована другим пользователем"); // добавить эксэпшн
        }
        else {
            newIssue.setDateIssue(LocalDateTime.now());
            if (newIssue.getRequiredDateReturn() == null) newIssue.setRequiredDateReturn(LocalDateTime.now().plusDays(30));
            issuedBookRepository.save(newIssue);
        }
    }

    @Override
    public void takeAwayBook(Long bookId) {
        IssuedBook issuedBook = issuedBookRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(bookId).get());
        issuedBook.setDateReturn(LocalDateTime.now());
        issuedBookRepository.save(issuedBook);
    }






















    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
