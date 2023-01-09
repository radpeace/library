package com.radpeace.library.dto.model;

import com.radpeace.library.dto.mapper.BookMapper;
import com.radpeace.library.entity.IssuedBook;
import com.radpeace.library.entity.Reader;
import com.radpeace.library.repository.BookRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class IssuedBookDto {
    private Long id;
    private ReaderDto readerId;
    private BookDtoForIssue bookId;
    private LocalDateTime dateIssue;
    private LocalDateTime requiredDateReturn;
    private LocalDateTime dateReturn;
}
