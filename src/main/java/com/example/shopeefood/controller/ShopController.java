package com.example.shopeefood.controller;

import com.example.shopeefood.model.*;
import com.example.shopeefood.service.category.ICategoryService;
import com.example.shopeefood.service.city.ICityService;
import com.example.shopeefood.service.menu.IMenuService;
import com.example.shopeefood.service.shop.IShopService;
import com.example.shopeefood.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/shops")
public class ShopController {
    @Autowired
    private IShopService iShopService;
    @Autowired
    private IMenuService iMenuService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICityService iCityService;
    @Autowired
    private ICategoryService iCategoryService;
    @Value("/home/dang/cloneCode/src/main/resources/static/img/")
    private String fileUpload;
    public MultipartFile multipartFile;


    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops(){
        List<Shop> shops = (List<Shop>) iShopService.findAll();
        if (shops.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(shops, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Shop> findById(@PathVariable Long id) {
        Optional<Shop> shopOptional = iShopService.findById(id);
        if (shopOptional.isPresent()) {
            Shop shop = shopOptional.get();
            return new ResponseEntity<>(shop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Shop> createShop(@ModelAttribute ShopFile shopFile)  throws IOException {
         multipartFile = shopFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(shopFile.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        Shop shop = new Shop(
                shopFile.getId(),
                shopFile.getName(),
                shopFile.getAddress(),
                shopFile.getPhoneNumber(),
                shopFile.getEmail(),
                fileName,
                shopFile.getTimeStart(),
                shopFile.getTimeEnd(),
                shopFile.getIdCity(),
                shopFile.getIdCategory(),
                shopFile.getIdUser(),
                localDateTime,
                localDateTime
        );
        return new ResponseEntity<>(iShopService.save(shop), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable Long id, @ModelAttribute ShopFile shopFile)  throws IOException {
        multipartFile = shopFile.getImage();
       Shop shop = iShopService.findById(id).get();

        String fileName = multipartFile.getOriginalFilename();
        Shop originalMovie = iShopService.findById(shopFile.getId()).get();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (multipartFile.isEmpty()) {
            // Không có tệp ảnh mới được chọn, sử dụng ảnh gốc

             shop = new Shop(
                    shopFile.getId(),
                    shopFile.getName(),
                    shopFile.getAddress(),
                    shopFile.getPhoneNumber(),
                    shopFile.getEmail(),
                    originalMovie.getImage(),
                    shopFile.getTimeStart(),
                    shopFile.getTimeEnd(),
                    shopFile.getIdCity(),
                    shopFile.getIdCategory(),
                    shop.getIdUser(),
                    shop.getCreatedAt(),
                    localDateTime
            );

            return new ResponseEntity<>(iShopService.save(shop), HttpStatus.CREATED);
        } else {
            // Có tệp ảnh mới được chọn, sao chép và cập nhật thông tin của shop
            try {
                FileCopyUtils.copy(shopFile.getImage().getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             shop = new Shop(
                    shopFile.getId(),
                    shopFile.getName(),
                    shopFile.getAddress(),
                    shopFile.getPhoneNumber(),
                    shopFile.getEmail(),
                     fileName,
                    shopFile.getTimeStart(),
                    shopFile.getTimeEnd(),
                    shopFile.getIdCity(),
                    shopFile.getIdCategory(),
                    shopFile.getIdUser(),
                    shop.getCreatedAt(),
                    localDateTime
            );
            return new ResponseEntity<>(iShopService.save(shop), HttpStatus.CREATED);
        }
    }
    @GetMapping("/menu/{idShop}")
    public ResponseEntity<List<Product>> getAllMenuByIdShop(@PathVariable Long idShop) {
        Optional<Shop> shop = iShopService.findById(idShop);
        List<Menu> menus = (List<Menu>) iMenuService.findAllByIdShop(shop.get());
        List<Product> products = new ArrayList<>();
        for (Menu menu : menus) {
            products = menu.getProducts();
            System.out.printf(products.toString());
        }
            return new ResponseEntity<>(products, HttpStatus.OK);
    }

}





