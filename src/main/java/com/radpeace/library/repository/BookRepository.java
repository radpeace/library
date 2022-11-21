package com.radpeace.library.repository;

import com.radpeace.library.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findByVendor (String vendor);

    @Query(value = "select b.id, b.title, b.description, b.vendor, b.genres, b.library_id from book as b join issued_book as ib on b.id = ib.book_id where ib.reader_id = ?1 and ib.date_return isnull", nativeQuery = true)
    List<BookEntity> findMyBooks (Long readerId);

}
