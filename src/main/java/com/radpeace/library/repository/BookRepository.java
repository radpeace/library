package com.radpeace.library.repository;

import com.radpeace.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByVendor (String vendor);

    @Query(value = "select b from book as b join issued_book as ib on b.id = ib.book_id where ib.reader_id = ?1 and ib.date_return isnull", nativeQuery = true)
    List<Book> findMyBooks (Long readerId);

}
