package com.radpeace.library.service;

import com.radpeace.library.entity.*;
import com.radpeace.library.exception.NotFoundException;
import com.radpeace.library.dto.model.BookDto;

import java.util.List;
import java.util.Set;

public interface ReaderService {

    List<BookDto> getBooks(int offset, int pageSize, String field);
//    List<BookDto> findBooksByTitle (String field);
//    List<BookDto> findBooksByGenre (String field);
//    List<BookDto> findBooksByAuthor (String field);
    Set<BookDto> findBooksByField(String field);
    BookDto getBook(Long bookId) throws NotFoundException;
    List<BookDto> getMyBooks();
    List<Book> getReservedBooks();
    List<Comment> getComments ();
    Comment getComment (Long commentId);
    Comment addComment(Comment newComment, Long id);
//    Booking addBooking(Booking newBooking, Long id);
//    void deleteBooking (Long bookingId);
//    Queue addQueue(Queue newQueue, Long id);
//    void removeQueue (Long queueId);





}
