package com.radpeace.library.dto.mapper;

import com.radpeace.library.dto.model.BookDto;
import com.radpeace.library.dto.model.BookDtoForIssue;
import com.radpeace.library.entity.Book;

import java.util.stream.Collectors;

public class BookMapper {
    public static BookDto toBookDto (Book book) {
        return new BookDto()
                .setId(book.getId())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setDescription(book.getDescription())
                .setCover(book.getCover())
                .setFile(book.getFile())
                .setBookType(TypeMapper.toTypeDto(book.getBookType()))
                .setGenres(book.getGenres().stream().map(GenreMapper::toGenreDto)
                        .collect(Collectors.toList()))
                .setAuthors(book.getAuthors().stream().map(AuthorMapper::toAuthorDto)
                        .collect(Collectors.toList()))
                .setLibraryId(LibraryMapper.toLibraryDto(book.getLibraryId()));
    }

    public static BookDtoForIssue toBookDtoForIssuedBook (Book book) {
        return new BookDtoForIssue()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setLibraryId(LibraryMapper.toLibraryDto(book.getLibraryId()));
    }
}
