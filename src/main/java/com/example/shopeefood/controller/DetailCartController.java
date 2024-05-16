package com.example.shopeefood.controller;

import com.example.shopeefood.model.DetailCart;
import com.example.shopeefood.model.Shop;
import com.example.shopeefood.model.User;
import com.example.shopeefood.service.detailcart.IDetailCartService;
import com.example.shopeefood.service.shop.IShopService;
import com.example.shopeefood.service.user.IUserService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detailCart/{idShop}/{idUser}")
public class DetailCartController {
    @Autowired
    private IDetailCartService iDetailCartService;
    @Autowired
    private IShopService iShopService;
    @Autowired
    private IUserService iUserService;
    @GetMapping
    public ResponseEntity<List<DetailCart>> getDetailCart(@PathVariable Long idShop, @PathVariable Long idUser) {
        Optional<User> user = iUserService.findById(idUser);
        Optional<Shop> shop = iShopService.findById(idShop);
        List<DetailCart> detailCarts = (List<DetailCart>) iDetailCartService.findAllByShopAndCart(shop.get(),user.get());
        if (detailCarts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(detailCarts, HttpStatus.OK);
        }
    }
}
