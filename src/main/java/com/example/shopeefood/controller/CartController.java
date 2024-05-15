package com.example.shopeefood.controller;

import com.example.shopeefood.model.Cart;
import com.example.shopeefood.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private ICartService iCartService;
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> cartList = (List<Cart>) iCartService.findAll();
        if (cartList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(cartList, HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(iCartService.save(cart), HttpStatus.CREATED);
    }
}
