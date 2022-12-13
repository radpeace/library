package com.radpeace.library.dto.model;

import com.radpeace.library.entity.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class AuthorDto {
    private String name;
}
