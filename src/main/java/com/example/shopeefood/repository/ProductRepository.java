package com.example.shopeefood.repository;

import com.example.shopeefood.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product>findAllByNameContaining(Pageable pageable, String name);
}