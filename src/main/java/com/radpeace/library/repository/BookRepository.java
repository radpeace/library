package com.radpeace.library.repository;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//    @Query("select b from Book b where b.issuedBooks in :issuedBooks")
//    List<Book> findAllByIssuedBooksIn (@Param("issuedBooks") List<IssuedBook> issuedBooks);

    @Query(value = "select b.id, b.title, b.description, b.vendor from book as b join issued_book as ib on b.id = ib.book_id where ib.reader_id = ?1", nativeQuery = true)
    List<Book> findMyBooks (Long readerId);

}
