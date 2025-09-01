package org.apiDQ.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponse {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;

    @JsonProperty("isActive")
    private boolean active;
}