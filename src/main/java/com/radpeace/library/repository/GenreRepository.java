package com.radpeace.library.repository;

import com.radpeace.library.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByGenreBookId (Long bookId);

    List<Genre> findAllByGenreBookId (Long bookId);

//    @Query("delete from Book b where b.genreBookId = ?1")
//    void deleteGenres (Long bookId);

    void deleteByGenreBookId (Long bookId);
    void deleteAllByGenreBookId (Long bookId);
    Genre findByTitle (String title);

}
