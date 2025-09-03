package org.apiDQ.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBooksRequest {
    private String userId;
    private CollectionOfIsbns[] collectionOfIsbns;

    @Data
    @AllArgsConstructor
    public static class CollectionOfIsbns {
        private String isbn;
    }
}