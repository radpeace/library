package com.radpeace.library.dto.mapper;

import com.radpeace.library.dto.model.GenreDto;
import com.radpeace.library.entity.Genre;
import lombok.Getter;
import lombok.Setter;

public class GenreMapper {
    public static GenreDto toGenreDto(Genre genre) {
        return new GenreDto()
                .setTitle(genre.getTitle());
//                .setGenreBookId(genre.getGenreBookId().getId());
    }
}
