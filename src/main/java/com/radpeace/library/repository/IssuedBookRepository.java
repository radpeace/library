package com.radpeace.library.repository;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {

//    IssuedBook findByBookId (Book book);

//    @Query("select ib from IssuedBook ib where ib.readerId = :readerID")
//    List<IssuedBook> findAllByReaderId (@Param("readerId") Long readerId);

    IssuedBook findByBookId (Book bookId);
    IssuedBook findByReaderId (Reader readerId);


}
