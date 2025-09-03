package org.api.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String token;
}
