package com.casualthoughts.crud_app_with_security.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddNewUserRequest {
    private String username;
    private String password;
    private String role;
}
