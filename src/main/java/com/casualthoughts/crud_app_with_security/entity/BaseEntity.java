package com.casualthoughts.crud_app_with_security.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
/**
 * Instead of adding date fields to every class,
 * we can use  a @MappedSuperclass that our Product and UserInfo entities can extend.
 */
public abstract class BaseEntity {

    @CreatedDate
    @Column(nullable = false,updatable = false)
    @Schema(description = "createdAt", example = "2026-03-29 14:06:41.10358", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Schema(description = "updatedAt", example = "2026-03-29 14:06:41.10358", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;
    @CreatedBy
    @Column(nullable = false,updatable = false)
    @Schema(description = "createdBy", example = "testuser", accessMode = Schema.AccessMode.READ_ONLY)
    private String createdBy;
    @LastModifiedBy
    @Schema(description = "updatedBy", example = "newUser", accessMode = Schema.AccessMode.READ_ONLY)
    private String updatedBy;

}
