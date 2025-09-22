package org.api.responseDTO;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewPostDto {
    private String title;
    private String body;
    private String select1;
    private String uniquePost;
}
