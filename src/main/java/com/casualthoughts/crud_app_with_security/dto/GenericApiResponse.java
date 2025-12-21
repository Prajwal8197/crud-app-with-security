package com.casualthoughts.crud_app_with_security.dto;

import lombok.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericApiResponse<T> {
    private boolean success;
    private String message;
    private Integer statusCode;
    private T data;

    ///  static helper for success
    public static  <T> ResponseEntity<GenericApiResponse<?>> success(String message, Integer statusCode, T data) {
       GenericApiResponse<T> response = new GenericApiResponse<>(true,message,statusCode,data);
        return ResponseEntity.status(statusCode).body(response);
    }

    /// static helper for error
    public static <T> ResponseEntity<GenericApiResponse<?>> error(String message, Integer statusCode) {
        Map<String, Object> errorResponse = new HashMap<>();
        GenericApiResponse<T> response  = new GenericApiResponse<>(false,message,statusCode,null);
        return ResponseEntity.status(statusCode).body(response);
    }

}
