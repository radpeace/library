package com.radpeace.library.service;

import com.radpeace.library.entity.Booking;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.exception.AlreadyBookingException;
import com.radpeace.library.exception.AlreadyIssuedException;
import com.radpeace.library.repository.BookRepository;
import com.radpeace.library.repository.BookingRepository;
import com.radpeace.library.repository.IssuedBookRepository;
import com.radpeace.library.repository.ReaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class BookingService {

    private Long readerId = 1L;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    public Booking addBooking(Booking newBooking, Long id) {
        Booking booking = bookingRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(id).get());
        IssuedBook issuedBook = issuedBookRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(id).get());

        if (issuedBook != null && issuedBook.getDateReturn() == null)
            throw new AlreadyIssuedException("Ошибка, книга уже выдана");
        if (booking != null && LocalDateTime.now().isBefore(booking.getDateFinish()))
            throw new AlreadyBookingException("Ошибка, книга уже забронирована");
        else {
            newBooking.setReaderId(readerRepository.findById(readerId).get());
            newBooking.setBookId(bookRepository.findById(id).get());
            newBooking.setDateStart(LocalDateTime.now());
            newBooking.setDateFinish(LocalDateTime.now().plusSeconds(90));
            return bookingRepository.save(newBooking);
        }
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

//    @Scheduled(fixedDelay = 5000L)
//    public void checkBooking() {
//        bookingRepository.findAll().stream().forEach(booking -> {
//            if (LocalDateTime.now().isAfter(booking.getDateFinish())){
//                deleteBooking(booking.getId());
//                log.info("Бронь закончена");
//            }
//        });
//    }
}
