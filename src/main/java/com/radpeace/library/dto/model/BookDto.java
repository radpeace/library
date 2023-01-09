package com.radpeace.library.dto.model;

import com.radpeace.library.entity.Library;
import com.radpeace.library.entity.Type;
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
    private String isbn;
    private String title;
    private String description;
    private String cover;
    private String file;
    private TypeDto bookType;
    private List<GenreDto> genres;
    private List<AuthorDto> authors;
    private LibraryDto libraryId;
    private String inExist;
}
