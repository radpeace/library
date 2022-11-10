package com.radpeace.library.repository;

import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook, Integer> {

    IssuedBook findByReaderId (Reader readerId);

    IssuedBook findByBookId (Book bookId);

    List<IssuedBook> findAllByReaderId (int readerId);


}
