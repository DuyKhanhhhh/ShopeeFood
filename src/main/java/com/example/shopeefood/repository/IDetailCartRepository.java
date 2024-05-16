package com.example.shopeefood.repository;

import com.example.shopeefood.model.DetailCart;
import com.example.shopeefood.model.Shop;
import com.example.shopeefood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDetailCartRepository extends JpaRepository<DetailCart, Long> {
//    select * from detail_cart d JOIN cart c ON d.cart_id = c.id where shop_id = 11 and c.user_id = 1;
    @Query("SELECT d FROM DetailCart d join Cart c ON d.cart.id = c.id WHERE d.shop = ?1 AND c.idUser = ?2 ")
    Iterable<DetailCart> findAllByShopAndCart(Shop shop, User user);
}
