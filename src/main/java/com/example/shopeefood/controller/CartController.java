package com.example.shopeefood.controller;

import com.example.shopeefood.model.Cart;
import com.example.shopeefood.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    ICartRepository cartRepository;
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable long id, @RequestBody Cart cart) {
        cart.setId(id);
        Cart cart1=cartRepository.save(cart);
        return new ResponseEntity<Cart>(cart1, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable long id) {
        cartRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Cart>> getCarts() {
        List<Cart> carts=cartRepository.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
}
