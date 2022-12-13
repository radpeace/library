package com.radpeace.library.dto.mapper;

import com.radpeace.library.dto.model.LibraryDto;
import com.radpeace.library.dto.model.ReaderDto;
import com.radpeace.library.entity.Library;
import com.radpeace.library.entity.Reader;

public class ReaderMapper {
    public static ReaderDto toReaderDto (Reader reader) {
        return new ReaderDto()
                .setSurname(reader.getSurname())
                .setName(reader.getName());
    }
}
