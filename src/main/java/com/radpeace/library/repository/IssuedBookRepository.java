package com.radpeace.library.repository;

import com.radpeace.library.entity.BookEntity;
import com.radpeace.library.entity.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {

//    IssuedBook findByBookId (BookEntity book);

//    @Query("select ib from IssuedBook ib where ib.readerId = :readerID")
//    List<IssuedBook> findAllByReaderId (@Param("readerId") Long readerId);


}
