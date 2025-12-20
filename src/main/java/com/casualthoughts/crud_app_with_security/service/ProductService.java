package com.casualthoughts.crud_app_with_security.service;

import com.casualthoughts.crud_app_with_security.entity.Product;
import com.casualthoughts.crud_app_with_security.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() { return repository.findAll(); }
    public Product getProduct(Long id) { return repository.findById(id).orElse(null); }
    public Product saveProduct(Product product) { return repository.save(product); }
}