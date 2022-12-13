package com.radpeace.library.dto.mapper;

import com.radpeace.library.dto.model.AuthorDto;
import com.radpeace.library.entity.Author;
import lombok.Getter;
import lombok.Setter;

public class AuthorMapper {
    public static AuthorDto toAuthorDto (Author author) {
        return new AuthorDto()
                .setName(author.getName());
    }
}
