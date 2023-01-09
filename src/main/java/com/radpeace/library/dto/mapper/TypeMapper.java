package com.radpeace.library.dto.mapper;

import com.radpeace.library.dto.model.LibraryDto;
import com.radpeace.library.dto.model.TypeDto;
import com.radpeace.library.entity.Library;
import com.radpeace.library.entity.Type;

public class TypeMapper {
    public static TypeDto toTypeDto (Type bookType) {
        return new TypeDto()
                .setId(bookType.getId())
                .setName(bookType.getName());
    }
}
