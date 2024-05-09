package com.example.shopeefood.service;

import com.example.shopeefood.model.Product;
import com.example.shopeefood.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public Product save(Product product) {
return iProductRepository.save(product);
    }

    @Override
    public void remote(Product product) {

    }

    @Override
    public Product delete(Long id) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {

        return iProductRepository.findById(id);

    }
}
