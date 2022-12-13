package com.radpeace.library.dto.mapper;

import com.radpeace.library.dto.model.LibraryDto;
import com.radpeace.library.entity.Library;

public class LibraryMapper {
    public static LibraryDto toLibraryDto (Library library) {
        return new LibraryDto()
                .setId(library.getId())
                .setName(library.getName());
    }
}
