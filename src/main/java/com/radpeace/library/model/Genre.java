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

    private String title;
    private Long genreBookId;

    public static Genre toModel(GenreEntity entity) {
        Genre genreModel = new Genre();
        genreModel.setTitle(entity.getTitle());
        genreModel.setGenreBookId(entity.getGenreBookId().getId());
        return genreModel;
    }

    public Genre() {
    }
}
