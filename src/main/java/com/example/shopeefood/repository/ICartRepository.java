package com.example.shopeefood.repository;

import com.example.shopeefood.model.Cart;
import com.example.shopeefood.model.DetailCart;
import com.example.shopeefood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ICartRepository extends JpaRepository<Cart, Long> {
    Iterable<Cart> findCartByIdUser(User idUser);
}