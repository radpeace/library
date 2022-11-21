package com.radpeace.library.repository;

import com.radpeace.library.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    GenreEntity findByGenreBookId (Long bookId);

    GenreEntity findByTitle (String title);

}
