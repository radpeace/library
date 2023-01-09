package com.radpeace.library.repository;

import com.radpeace.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase (String field);

    List<Book> findByDescriptionContainingIgnoreCase (String field);

    @Query(value = "select b.* from book as b join book_genres as bg on b.id = bg.book_id join genre as g ON bg.genre_id = g.id where upper(g.title) like upper('%'||:field||'%')", nativeQuery = true)
    List<Book> findByGenres (String field);

    @Query(value = "select b.* from book as b join book_authors as ba on b.id = ba.book_id join author as a ON ba.author_id = a.id where upper(a.name) like upper('%'||:field||'%')", nativeQuery = true)
    List<Book> findByAuthors (String field);

    Book findByIsbn (String isbn);

    @Query(value = "select b from book as b join issued_book as ib on b.id = ib.book_id where ib.reader_id = ?1 and ib.date_return isnull", nativeQuery = true)
    List<Book> findMyBooks (Long readerId);

}
