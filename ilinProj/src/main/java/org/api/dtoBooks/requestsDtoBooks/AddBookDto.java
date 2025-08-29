package org.api.dtoBooks.requestsDtoBooks;

import lombok.*;
import org.api.dtoBooks.responsesDtoBooks.IsbnDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookDto {
    private String userId;
    private List<IsbnDto> collectionOfIsbns;
}