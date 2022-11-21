package com.radpeace.library.model;

import com.radpeace.library.entity.BookEntity;
import com.radpeace.library.entity.GenreEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class Genre {

    private Long id;
    private String title;
    private BookEntity genreBookId;

    public static Genre toModel(GenreEntity entity) {
        Genre genreModel = new Genre();
        genreModel.setId(entity.getId());
        genreModel.setTitle(entity.getTitle());
        genreModel.setGenreBookId(entity.getGenreBookId());
        return genreModel;
    }

    public Genre() {
    }
}
