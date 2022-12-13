package com.radpeace.library.dto.mapper;

import com.radpeace.library.dto.model.IssuedBookDto;
import com.radpeace.library.entity.IssuedBook;

public class IssuedBookMapper {
    public static IssuedBookDto toIssuedBookDto (IssuedBook issuedBook) {
        return new IssuedBookDto()
                .setId(issuedBook.getId())
                .setReaderId(ReaderMapper.toReaderDto(issuedBook.getReaderId()))
                .setBookId(BookMapper.toBookDtoForIssuedBook(issuedBook.getBookId()))
                .setDateIssue(issuedBook.getDateIssue())
                .setRequiredDateReturn(issuedBook.getRequiredDateReturn())
                .setDateReturn(issuedBook.getDateReturn());
    }
}
