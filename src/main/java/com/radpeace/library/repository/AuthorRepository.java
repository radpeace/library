package com.radpeace.library.repository;

import com.radpeace.library.entity.Author;
import com.radpeace.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {



}
