package com.example.shopeefood.repository;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuRepository extends JpaRepository<Menu,Long> {
    Iterable<Menu> findAllByIdShop(Shop shop);
    @Query("SELECT m FROM Menu m WHERE m.idShop.id = :shopId")
    List<Menu> findMenuByIdShop(@Param("shopId") Long shopId);

}
