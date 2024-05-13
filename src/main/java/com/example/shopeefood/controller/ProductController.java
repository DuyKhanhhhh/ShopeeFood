package com.example.shopeefood.controller;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Product;
import com.example.shopeefood.model.ProductFile;
import com.example.shopeefood.repository.IProductRepository;
import com.example.shopeefood.service.IMenuService;
import com.example.shopeefood.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Value("${folder_upload}")
    private String fileUpload;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IMenuService iMenuService;
    @Autowired
    private IProductRepository productRepository;
    @GetMapping("/ProductListByMenuId/{id}")
    public ResponseEntity<List<Product>> getListProduct(@PathVariable Long id) {
        List<Product>list=productRepository.findFoodByMenuId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/FindByPByName/{id}")
    public ResponseEntity<List<Product>> findByPName(@PathVariable Long id,@RequestParam("productName") String productName) {
        List<Product>list=productRepository.findFoodByMenuIdAndName(id, productName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> saveProduct(@ModelAttribute ProductFile productFile) throws IOException {
        MultipartFile multipartFile = productFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productFile.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(productFile.getId(), productFile.getName(), productFile.getPrice(), productFile.getQuantity(), "img" + fileName, productFile.getDetail(), productFile.getMenus(), productFile.getCreatedAt(), productFile.getUpdatedAt());
        Optional<Menu> menu = iMenuService.findById(product.getMenus().stream().count());
        if (menu != null) {
            product = iProductService.save(product);
            Set<Menu> menuSet = new HashSet<>();
            menuSet.add(menu.get());
            product.setMenus(menuSet);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/products/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
