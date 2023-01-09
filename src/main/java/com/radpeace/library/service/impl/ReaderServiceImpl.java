package com.radpeace.library.service.impl;

import com.radpeace.library.dto.mapper.BookMapper;
import com.radpeace.library.dto.model.BookDto;
import com.radpeace.library.entity.*;
import com.radpeace.library.exception.AlreadyBookingException;
import com.radpeace.library.exception.AlreadyIssuedException;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.exception.ResultIsEmptyException;
import com.radpeace.library.repository.*;
import com.radpeace.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private QueueRepository queueRepository;

    @Override
    public List<BookDto> getBooks(int offset, int pageSize, String field) {
        return
            bookRepository.findAll(PageRequest.of(offset - 1, pageSize).withSort(Sort.by(field)))
            .getContent()
            .stream().map(BookMapper::toBookDto).collect(Collectors.toList());
    }

//    @Override
//    public List<BookDto> findBooksByTitle(String field) {
//        return bookRepository.findByTitleContainingIgnoreCase(field).stream().map(BookMapper::toBookDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<BookDto> findBooksByGenre(String field) {
//        return bookRepository.findByGenres(field).stream().map(BookMapper::toBookDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<BookDto> findBooksByAuthor(String field) {
//        return bookRepository.findByAuthors(field).stream().map(BookMapper::toBookDto).collect(Collectors.toList());
//    }

    @Override
    public Set<BookDto> findBooksByField(String field) {
        Set<BookDto> books = new HashSet<>();
        books.addAll(bookRepository.findByTitleContainingIgnoreCase(field).stream().map(BookMapper::toBookDto).collect(Collectors.toSet()));
        books.addAll(bookRepository.findByDescriptionContainingIgnoreCase(field).stream().map(BookMapper::toBookDto).collect(Collectors.toSet()));
        books.addAll(bookRepository.findByGenres(field).stream().map(BookMapper::toBookDto).collect(Collectors.toSet()));
        books.addAll(bookRepository.findByAuthors(field).stream().map(BookMapper::toBookDto).collect(Collectors.toSet()));
        if (books.isEmpty()) throw new ResultIsEmptyException("По запросу '" + field + "' ничего не найдено");
        return books;

//        return bookRepository.findByAuthorsIgnoreCaseContaining(field).stream().map(BookMapper::toBookDto).collect(Collectors.toSet());

//        return Stream.of(findBooksByTitle(field), findBooksByGenre(field), findBooksByAuthor(field))
//                .flatMap(x -> x.stream())
//                .collect(Collectors.toSet());

//        return new HashSet<BookDto>(
//            bookRepository.findByTitle(field).stream().map(BookMapper::toBookDto).collect(Collectors.toList())
//            .addAll(bookRepository.findByGenres(field).stream().map(BookMapper::toBookDto).collect(Collectors.toList()))
//                    .addAll
//        );
    }


    @Override
    public BookDto getBook(Long bookId) {
        IssuedBook issuedBook = issuedBookRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(bookId).get());
        Booking booking = bookingRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(bookId).get());

        BookDto bookDto = BookMapper.toBookDto(bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Книга с таким идентификатором не найдена")));
        if (issuedBook != null && issuedBook.getDateReturn() == null) {
            throw new AlreadyIssuedException("Встать в очередь");
        }
        else if (booking != null
                && booking.getReaderId().getId() != readerId
                && LocalDateTime.now().isBefore(booking.getDateFinish())) {
            throw new AlreadyIssuedException("Ошибка, книга уже забронирована"); // добавить эксэпшн
        }
        return bookDto;

    }

    @Override
    public List<BookDto> getMyBooks() {
//        List<Book> myBooks = bookRepository.findMyBooks(readerId);
        return bookRepository.findMyBooks(readerId).stream().map(BookMapper::toBookDto).collect(Collectors.toList());
    }

    @Override
    public List<Book> getReservedBooks() {
        return null;
    }

    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).get();
    }

    @Override
    public Comment addComment(Comment newComment, Long id) {
        newComment.setCommentBookId(bookRepository.findById(id).get());
        newComment.setCommentReaderId(readerRepository.findById(readerId).get());
//        newComment.setCommentBookId(bookRepository.findById(id).get());
//        newComment.setCommentReaderId(readerRepository.findById(readerId).get());
        return commentRepository.save(newComment);
    }

}
