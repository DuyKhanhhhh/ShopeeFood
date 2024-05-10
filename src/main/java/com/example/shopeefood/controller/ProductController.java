package com.example.shopeefood.controller;

import com.example.shopeefood.model.Product;
import com.example.shopeefood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productApi")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<Page<Product>> getProduct(@PageableDefault(value = 3) Pageable pageable,
                                                    @RequestParam(required = false) String search) {
        Page<Product>list=productService.findAllByName(pageable, search);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
