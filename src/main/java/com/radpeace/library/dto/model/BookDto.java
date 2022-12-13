package com.radpeace.library.dto.model;

import com.radpeace.library.entity.Library;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String vendor;
    private String title;
    private String description;
    private List<GenreDto> genres;
    private List<AuthorDto> authors;
    private LibraryDto libraryId;
}
