package com.radpeace.library.repository;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findTopByBookIdOrderByIdDesc(Book id);
}
