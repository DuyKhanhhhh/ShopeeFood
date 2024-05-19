package com.example.shopeefood.controller;

import com.example.shopeefood.model.*;
import com.example.shopeefood.service.cart.ICartService;
import com.example.shopeefood.service.detailcart.IDetailCartService;
import com.example.shopeefood.service.product.IProductService;
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
@RequestMapping("/api/detailCart")
public class DetailCartController {
    @Autowired
    private IDetailCartService iDetailCartService;
    @Autowired
    private IShopService iShopService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICartService iCartService;
    @Autowired
    private IProductService iProductService;
    @GetMapping("/{idShop}/{idUser}")
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
    @PostMapping("/{idUser}/{idShop}/{idProduct}")
    public ResponseEntity<DetailCart> saveDetailCart(@PathVariable long idShop, @PathVariable long idUser, @PathVariable Long idProduct) {
        Optional<Shop> shop = iShopService.findById(idShop);
        Optional<Product> product = iProductService.findById(idProduct);
        Optional<Cart> cart = iCartService.findById(idUser);
        DetailCart detailCart = new DetailCart(product.get(),1,shop.get(),cart.get());
        iDetailCartService.save(detailCart);
        return new ResponseEntity<>(detailCart, HttpStatus.CREATED);
    }
}
