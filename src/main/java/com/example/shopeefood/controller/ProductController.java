package com.example.shopeefood.controller;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Product;
import com.example.shopeefood.model.ProductFile;
import com.example.shopeefood.service.menu.IMenuService;
import com.example.shopeefood.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Value("E:/java/ShopeeFood/src/main/resources/static/img/")
    private String fileUpload;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IMenuService iMenuService;


    @PostMapping()
    public ResponseEntity<Product> saveProduct(@ModelAttribute ProductFile productFile) throws IOException {
        MultipartFile multipartFile = productFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productFile.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        LocalDateTime localDateTime = LocalDateTime.now();

        Product product = new Product(
                productFile.getId(),
                productFile.getName(),
                productFile.getPrice(),
                productFile.getQuantity(),
                "/img"+fileName,
                productFile.getDetail(),
                productFile.getMenus(),
                localDateTime,
                localDateTime);
        Optional<Menu> menu = iMenuService.findById(product.getMenus().stream().count());
            product = iProductService.save(product);
            Set<Menu> menuSet = new HashSet<>();
            menuSet.add(menu.get());
            product.setMenus(menuSet);
            return new ResponseEntity<>(product, HttpStatus.CREATED) ;


    }

}