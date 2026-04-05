package com.casualthoughts.crud_app_with_security.service;

import com.casualthoughts.crud_app_with_security.dto.GenericApiResponse;
import com.casualthoughts.crud_app_with_security.entity.Product;
import com.casualthoughts.crud_app_with_security.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() { return repository.findAll(); }
    public Product getProduct(Long id) { return repository.findById(id).orElse(null); }
    public List<Product> saveProduct(List<Product> products) { return repository.saveAll(products); }

    public ResponseEntity<GenericApiResponse<?>> getProductPage(Pageable pageable) {

        Page<Product> productPage = repository.findAll(pageable);

        return GenericApiResponse.success("Products fetched Succesfully",200, productPage);
    }

}