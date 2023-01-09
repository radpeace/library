package com.radpeace.library.service;

import com.radpeace.library.entity.Booking;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.entity.Queue;
import com.radpeace.library.exception.AlreadyExistException;
import com.radpeace.library.exception.AlreadyIssuedException;
import com.radpeace.library.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Slf4j
public class QueueService {

    private Long readerId = 1L;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    public Queue addQueue(Queue newQueue, Long id) {
        Queue queue = queueRepository.findByBookId(bookRepository.findById(id).get());
        IssuedBook issuedBook = issuedBookRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(id).get());
        Booking booking = bookingRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(id).get());

        if (queue != null && queue.getReaderId().getId() == readerId)
            throw new AlreadyExistException("Вы уже в очереди");
        if (issuedBook != null && issuedBook.getDateReturn() == null && issuedBook.getReaderId().getId() == readerId)
            throw new AlreadyExistException("Книга уже у вас на руках");
        if (booking != null && LocalDateTime.now().isBefore(booking.getDateFinish()) && booking.getReaderId().getId() == readerId)
            throw new AlreadyExistException("Книга уже забронирована вами");
        newQueue.setReaderId(readerRepository.findById(readerId).get());
        newQueue.setBookId(bookRepository.findById(id).get());
        return queueRepository.save(newQueue);
    }

    public void removeQueue(Long queueId) {
        queueRepository.deleteById(queueId);
    }

    @Scheduled(fixedDelay = 5000L)
    public void checkQueue() {
        queueRepository.findAll().stream().forEach(queue -> {
            Booking booking = bookingRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(queue.getBookId().getId()).get());
            IssuedBook issuedBook = issuedBookRepository.findTopByBookIdOrderByIdDesc(bookRepository.findById(queue.getBookId().getId()).get());

            if ((issuedBook == null || issuedBook.getDateReturn() != null)
                    && (booking == null || LocalDateTime.now().isAfter(booking.getDateFinish()))) {

                Booking newBooking = new Booking();
                newBooking.setReaderId(readerRepository.findById(queue.getReaderId().getId()).get());
                newBooking.setBookId(bookRepository.findById(queue.getBookId().getId()).get());
                newBooking.setDateStart(LocalDateTime.now());
                newBooking.setDateFinish(LocalDateTime.now().plusSeconds(90));

                bookingRepository.save(newBooking);
                queueRepository.deleteById(queue.getId());
            }
        });
    }
}
