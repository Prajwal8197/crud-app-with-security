package com.casualthoughts.crud_app_with_security.repository;

import com.casualthoughts.crud_app_with_security.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
