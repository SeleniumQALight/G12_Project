package org.api.dtoBooks.responsesDtoBooks;

import lombok.*;

import java.util.List;

@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksListDto {
    private List<BookDto> books;
}
