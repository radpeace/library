package com.radpeace.library.model;

import com.radpeace.library.entity.BookEntity;
import com.radpeace.library.entity.Library;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Book {
    private Long id;
    private String vendor;
    private String title;
    private String description;
    private List<Genre> genres;



    private Library libraryId;

    public static Book toModel (BookEntity entity) {
        Book bookModel = new Book();
        bookModel.setId(entity.getId());
        bookModel.setVendor(entity.getVendor());
        bookModel.setTitle(entity.getTitle());
        bookModel.setDescription(entity.getDescription());
        bookModel.setGenres(entity.getGenres().stream().map(Genre::toModel)
                .collect(Collectors.toList()));
        bookModel.setLibraryId(entity.getLibraryId());
        return bookModel;
    }

    public Book() {
    }
}
