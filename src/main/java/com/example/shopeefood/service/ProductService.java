package com.example.shopeefood.service;

import com.example.shopeefood.model.Product;
import com.example.shopeefood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Page<Product> findAllByName(Pageable pageable,String name) {
        return productRepository.findAllByNameContaining(pageable,name);
    }
}
