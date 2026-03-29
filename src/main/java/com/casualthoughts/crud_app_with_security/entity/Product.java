package com.casualthoughts.crud_app_with_security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Unique ID of the product", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Name of the product", example = "Wireless Mouse", required = true)
    private String name;
    @Schema(description = "Product description", example = "Ergonomic and wireless mouse")
    private String description;
    @Schema(description = "Quantity of the product", example = "200")
    private Integer qty;
    @Schema(description = "Price of the product", example = "300")
    private Double price;
}
