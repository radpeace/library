package com.radpeace.library.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class BookDtoForIssue {
    private String vendor;
    private String title;
    private LibraryDto libraryId;
}
