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
public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {

    IssuedBook findByBookId (Long bookId);

//    @Query("select ib from IssuedBook ib where ib.readerId = :readerID")
//    List<IssuedBook> findAllByReaderId (@Param("readerId") Long readerId);


}
