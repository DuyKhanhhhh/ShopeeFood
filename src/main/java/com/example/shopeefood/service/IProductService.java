package com.example.shopeefood.service;

import com.example.shopeefood.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<Product> findAllByName(Pageable pageable,String name);
}
