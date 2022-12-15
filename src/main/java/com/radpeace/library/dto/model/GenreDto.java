package com.radpeace.library.dto.model;

import com.radpeace.library.entity.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class GenreDto {
    private String title;
//    private Long genreBookId;
}
