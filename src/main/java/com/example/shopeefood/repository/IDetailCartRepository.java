package com.example.shopeefood.repository;

import com.example.shopeefood.model.DetailCart;
import com.example.shopeefood.model.Product;
import com.example.shopeefood.model.Shop;
import com.example.shopeefood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IDetailCartRepository extends JpaRepository<DetailCart, Long> {
    @Query("SELECT d FROM DetailCart d join Cart c ON d.cart.id = c.id WHERE d.shop = ?1 AND c.idUser = ?2 ")
    Iterable<DetailCart> findAllByShopAndCart(Shop shop, User user);
    DetailCart findDetailCartByProduct(Product product);
}
