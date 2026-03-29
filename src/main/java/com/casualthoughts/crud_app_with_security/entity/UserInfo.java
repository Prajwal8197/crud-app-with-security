package com.casualthoughts.crud_app_with_security.entity;

import com.casualthoughts.crud_app_with_security.constant.Role;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique ID of the user", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Column(unique = true, nullable = false)
    @Schema(description = "username", example = "user123", accessMode = Schema.AccessMode.READ_ONLY)
    private String username;
    @Schema(description = "password", example = "test1234", accessMode = Schema.AccessMode.READ_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
