package com.casualthoughts.crud_app_with_security.controller;

import com.casualthoughts.crud_app_with_security.entity.Product;
import com.casualthoughts.crud_app_with_security.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Management", description = "Operations related to product catalog")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
public class ProductController {

    @Autowired
    private ProductService service;

    @Operation(
            summary = "Welcome public endpoint",
            description = "Accessible to all users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Something went wrong on the server")
    })
    @GetMapping("/welcome")
    public String welcome() { return "Welcome! This endpoint is public."; }

    @Operation(
            summary = "Get all Products",
            description = "Retieves all products from the database. Accessible to all users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER')")
    public List<Product> getAllProducts() {
        return service.getProducts();
    }


    @Operation(
            summary = "Get individual product by id",
            description = "Retieves product by id from the database. Accessible to all users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER')")
    public Product getProductById(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @Operation(
            summary = "Add a new product",
            description = "Saves a product to the database. Requires ROLE_ADMIN or ROLE_MANAGER."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product data")
    })
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }
}